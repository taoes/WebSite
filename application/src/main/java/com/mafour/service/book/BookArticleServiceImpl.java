package com.mafour.service.book;

import static java.lang.String.format;

import com.mafour.dao.book.BookArticleDO;
import com.mafour.service.book.bean.BookArticle;
import com.mafour.service.book.converter.BookContentConverter;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.book.yuque.YuqueDoc.Book;
import com.mafour.service.book.yuque.YuqueDoc.Data;
import com.mafour.service.utils.HttpUtils;
import com.mafour.tunnel.BookArticleReadTunnel;
import com.mafour.tunnel.BookContentTunnel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookArticleServiceImpl implements BookArticleService {

  private final RedissonClient redissonClient;

  private final BookContentTunnel contentTunnel;

  private final BookContentConverter converter;

  private final BookArticleReadTunnel readTunnel;

  @Override
  @SneakyThrows
  public YuqueDoc findByCategoryId(String book, String slug) {
    // 访问记录中新增阅读量
    Integer count = readTunnel.createOrIncrement(book, slug);

    // 读取缓存数据
    String cacheKey = String.format("WEB:CATEGORY:%s:CONTENT:%s", book, slug);
    RBucket<YuqueDoc> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      return bucket.get().setCount(count);
    }

    // 没有缓存则直接从语雀API中获取，注意添加并发锁
    String lockKey = "WEB:LOCK:" + cacheKey;
    RLock lock = redissonClient.getLock(lockKey);
    try {
      lock.lock(10, TimeUnit.SECONDS);
      bucket = redissonClient.getBucket(cacheKey);
      if (bucket.isExists()) {
        return bucket.get().setCount(count);
      }
      YuqueDoc yuqueDoc = findFromApi(book, slug);
      this.saveArticle(yuqueDoc);
      // 保存缓存并且设置有效期
      bucket.set(yuqueDoc, yuqueDoc.getAbilities() == null ? 1 : 30, TimeUnit.DAYS);
      return yuqueDoc.setCount(count);
    } finally {
      if (lock.isLocked()) {
        lock.unlock();
      }
    }
  }

  @Override
  public String findSearchKey(String slug) {
    return contentTunnel.getSearchBySlug(slug);
  }

  @Override
  public void recommend(boolean set, String slug) {
    contentTunnel.updateRecommend(set, slug);
  }

  @Override
  public List<BookArticle> recommendList() {
    List<BookArticleDO> bookArticleDOS = contentTunnel.getRecommend();
    return bookArticleDOS.stream().map(converter::converterFrom).collect(Collectors.toList());
  }

  @SneakyThrows
  private YuqueDoc findFromApi(String book, String slug) {
    String url = format("https://www.yuque.com/api/v2/repos/zhoutao123/%s/docs/%s", book, slug);
    Map<String, String> headers = new HashMap<>();
    headers.put("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX");
    headers.put("User-Agent", "www.zhoutao123.com");
    headers.put("Content-Type", "application/json");

    YuqueDoc yuqueDoc = HttpUtils.post(url, headers, YuqueDoc.class);
    if (yuqueDoc != null) {
      return yuqueDoc;
    }
    log.info("获取文章数据【book={} slug={}】 失败", book, slug);
    yuqueDoc = new YuqueDoc();
    return yuqueDoc.setData(new Data().setBody_html("<h1 style='margin:50px'>文章正在紧急准备中</h1>"));
  }

  /** 保存文章记录到数据库中 */
  private void saveArticle(YuqueDoc doc) {
    BookArticle content = new BookArticle();
    Data data = doc.getData();
    Book book = doc.getData().getBook();
    content
        .setId(doc.getData().getId())
        .setSlug(data.getSlug())
        .setBook(book.getSlug())
        .setBookName(book.getName())
        .setSlugName(data.getTitle())
        .setCover(data.getCover())
        .setUpdatedAt(data.getUpdated_at())
        .setBodyHtml(data.getBody_html())
        .setCreatedAt(data.getCreated_at())
        .setWordCount(data.getWord_count())
        .setDescription(data.getDescription())
        .setMkContent(data.getBody())
        .setHtmlContent(data.getBody_html());
    contentTunnel.saveOrUpdated(converter.converterTo(content));
  }
}
