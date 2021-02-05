package com.mafour.api.service.book;

import static java.lang.String.format;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mafour.api.dao.dao.book.BookArticleDO;
import com.mafour.api.dao.tunnel.BookArticleReadTunnel;
import com.mafour.api.dao.tunnel.BookContentTunnel;
import com.mafour.api.service.book.bean.BookArticle;
import com.mafour.api.service.book.yuque.YuqueDoc;
import com.mafour.api.service.book.yuque.YuqueDoc.Book;
import com.mafour.api.service.book.yuque.YuqueDoc.Data;
import com.mafour.api.service.redis.RedisService;
import com.mafour.api.service.utils.HttpUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookArticleServiceImpl implements BookArticleService {

  private final RedisService redisService;

  private final BookContentTunnel contentTunnel;

  private final com.mafour.api.service.book.converter.BookContentConverter converter;

  private final BookArticleReadTunnel readTunnel;

  @Override
  @SneakyThrows
  public YuqueDoc findByCategoryId(String book, String slug) {
    // 访问记录中新增阅读量
    Integer count = readTunnel.createOrIncrement(book, slug);

    // 读取缓存数据
    String cacheKey = String.format("WEB:CATEGORY:%s:CONTENT:%s", book, slug);
    boolean hasCache = redisService.hasKey(cacheKey);
    if (hasCache) {
      YuqueDoc yuqueDoc = redisService.find(cacheKey, new TypeReference<YuqueDoc>() {});
      return yuqueDoc.setCount(count);
    }

    // TODO 添加锁

    // 没有缓存则直接从语雀API中获取
    String lockKey = "WEB:LOCK:" + cacheKey;
    try {
      redisService.lock(lockKey, 20);
      // 获取到锁之后需要在判断下是否已经存在缓存
      hasCache = redisService.hasKey(cacheKey);
      if (hasCache) {
        YuqueDoc yuqueDoc = redisService.find(cacheKey, new TypeReference<YuqueDoc>() {});
        return yuqueDoc.setCount(count);
      }

      YuqueDoc yuqueDoc = findFromApi(book, slug);
      this.saveArticle(yuqueDoc);
      redisService.set(cacheKey, yuqueDoc, 0);
      return yuqueDoc.setCount(count);
    } finally {
      redisService.unlock(lockKey);
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
