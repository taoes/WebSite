package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.dao.book.BookArticleDO;
import com.mafour.service.book.bean.BookArticle;
import com.mafour.service.book.converter.BookContentConverter;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.book.yuque.YuqueDoc.Book;
import com.mafour.service.book.yuque.YuqueDoc.Data;
import com.mafour.tunnel.BookArticleReadTunnel;
import com.mafour.tunnel.BookContentTunnel;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookArticleServiceImpl implements BookArticleService {

  private final BookContentTunnel contentTunnel;

  private final BookContentConverter converter;

  private final RedissonClient redissonClient;

  private final BookArticleReadTunnel readTunnel;

  private final OkHttpClient client = new OkHttpClient();

  @Override
  @SneakyThrows
  public YuqueDoc findByCategoryId(String bookName, String slug) {
    Integer count = readTunnel.createOrIncrement(bookName, slug);

    String cacheKey = "CATEGORY:" + bookName + ":CONTENT:" + slug;
    RBucket<YuqueDoc> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      return bucket.get().setCount(count);
    }

    String lockKey = "LOCK:" + cacheKey;
    RLock lock = redissonClient.getLock(lockKey);
    try {
      lock.lock(10, TimeUnit.SECONDS);
      bucket = redissonClient.getBucket(cacheKey);
      if (bucket.isExists()) {
        return bucket.get().setCount(count);
      }

      YuqueDoc yuqueDoc = findFromYuqueApi(bookName, slug);
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
  private YuqueDoc findFromYuqueApi(String bookName, String slug) {
    Request request =
        new Request.Builder()
            .url("https://www.yuque.com/api/v2/repos/zhoutao123/" + bookName + "/docs/" + slug)
            .addHeader("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX")
            .addHeader("User-Agent", "www.zhoutao123.com")
            .addHeader("Content-Type", "application/json")
            .build();

    try (Response execute = client.newCall(request).execute()) {
      if (execute.isSuccessful()) {
        String string = Optional.ofNullable(execute.body().string()).orElse("{}");
        YuqueDoc yuqueDoc = JSON.parseObject(string, YuqueDoc.class);
        save(yuqueDoc);
        return yuqueDoc;
      } else {
        log.info("获取文章数据【book={} slug={}】 失败", bookName, slug);
        YuqueDoc yuqueDoc = new YuqueDoc();
        yuqueDoc.setData(new Data().setBody_html("<h1 style='margin:50px'>文章正在紧急准备中</h1>"));
        return yuqueDoc;
      }
    }
  }

  private void save(YuqueDoc doc) {
    BookArticle content = new BookArticle();
    Data data = doc.getData();
    Book book = doc.getData().getBook();
    content
        .setId(doc.getData().getId())
        .setBook(book.getSlug())
        .setBookName(book.getName())
        .setSlugName(data.getTitle())
        .setCover(data.getCover())
        .setSlug(data.getSlug())
        .setUpdatedAt(data.getUpdated_at())
        .setBodyHtml(data.getBody_html())
        .setCreatedAt(data.getCreated_at())
        .setWordCount(data.getWord_count())
        .setDescription(data.getDescription());
    contentTunnel.saveOrUpdated(converter.converterTo(content));
  }
}
