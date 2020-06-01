package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.dao.book.BookContentDO;
import com.mafour.exception.NotFoundException;
import com.mafour.service.book.bean.BookContent;
import com.mafour.service.book.converter.BookContentConverter;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.book.yuque.YuqueDoc.Data;
import com.mafour.tunnel.BookContentTunnel;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
public class BookContentServiceImpl implements BookContentService {

  private final BookContentTunnel contentTunnel;

  private final BookContentConverter converter;

  private final RedissonClient redissonClient;

  private final OkHttpClient client = new OkHttpClient();

  @Override
  public BookContent findByCategoryId(Long categoryId) {
    BookContentDO categoryDO = contentTunnel.getByCategoryId(categoryId);
    if (categoryDO == null) {
      throw new NotFoundException("抱歉，文件内容已经无法访问");
    }
    return converter.converterFrom(categoryDO);
  }

  @SneakyThrows
  @Override
  public YuqueDoc findByCategoryId(String bookName, String slug) {
    log.info("查找缓存 【book={} slug={}】", bookName, slug);
    String cacheKey = "CATEGORY:" + bookName + ":CONTENT:" + slug;
    RBucket<YuqueDoc> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      log.info("发现缓存，跳过请求语雀API,key={}", cacheKey);
      return bucket.get();
    }

    String lockKey = "LOCK:" + cacheKey;
    RLock lock = redissonClient.getLock(lockKey);
    try {
      lock.lock(10, TimeUnit.SECONDS);
      log.info("获取锁【{}】 完成", lockKey);
      // 再次检查cache是否存在
      bucket = redissonClient.getBucket(cacheKey);
      if (bucket.isExists()) {
        log.info("进入锁之后发现缓存，跳过请求语雀API,key={} 并尝试释放锁", cacheKey);
        return bucket.get();
      }

      log.info("未返现缓存，开始请求语雀API");
      Request request =
          new Request.Builder()
              .url("https://www.yuque.com/api/v2/repos/zhoutao123/" + bookName + "/docs/" + slug)
              .addHeader("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX")
              .addHeader("User-Agent", "www.zhoutao123.com")
              .addHeader("Content-Type", "application/json")
              .build();

      Response execute = client.newCall(request).execute();
      if (execute.isSuccessful()) {
        String string = Optional.ofNullable(execute.body().string()).orElse("{}");
        log.info("获取文章数据【book={} slug = {}】 完成", bookName, slug);
        YuqueDoc yuqueDoc = JSON.parseObject(string, YuqueDoc.class);
        bucket.set(yuqueDoc, 30, TimeUnit.DAYS);
        return yuqueDoc;
      } else {
        log.info("获取文章数据【book={} slug={}】 失败", bookName, slug);
        YuqueDoc yuqueDoc = new YuqueDoc();
        yuqueDoc.setData(new Data().setBody_html("<h1 style='margin:50px'>文章正在紧急准备中</h1>"));
        return yuqueDoc;
      }
    } finally {
      if (lock.isLocked()) {
        lock.unlock();
      }
      log.info("释放锁:【{}】完成", lockKey);
    }
  }
}
