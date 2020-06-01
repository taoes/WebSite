package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.service.book.bean.BookCategory;
import com.mafour.service.book.converter.BookCategoryConverter;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.tunnel.BookCategoryTunnel;
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
public class BookCategoryServiceImpl implements BookCategoryService {

  private final BookCategoryTunnel categoryTunnel;

  private final RedissonClient redissonClient;

  private final BookCategoryConverter converter;

  private final OkHttpClient client = new OkHttpClient();

  @SneakyThrows
  @Override
  public YuqueCategoryData findByBook(String yuqueName) {
    log.info("find info from redis: book = {}", yuqueName);
    String cacheKey = "CATEGORY:" + yuqueName;
    RBucket<YuqueCategoryData> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      log.info("发现缓存: key = {}", cacheKey);
      return bucket.get();
    }

    log.info("未发现缓存，开始请求语雀API, key= {}", cacheKey);

    String lockKey = "LOCK:" + cacheKey;
    RLock lock = redissonClient.getLock(lockKey);
    try {
      lock.lock();
      log.info("获取锁【{}】 完成", lockKey);
      bucket = redissonClient.getBucket(cacheKey);
      if (bucket.isExists()) {
        log.info("进入锁之后发现缓存: key = {}", cacheKey);
        return bucket.get();
      }

      Request request =
          new Request.Builder()
              .url("https://www.yuque.com/api/v2/repos/zhoutao123/" + yuqueName + "/toc")
              .addHeader("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX")
              .addHeader("User-Agent", "www.zhoutao123.com")
              .addHeader("Content-Type", "application/json")
              .build();

      Response execute = client.newCall(request).execute();

      if (execute.isSuccessful()) {
        String string = Optional.ofNullable(execute.body().string()).orElse("{}");
        log.info("获取文章数据【book={}】 完成", yuqueName);
        YuqueCategoryData categoryData = JSON.parseObject(string, YuqueCategoryData.class);
        bucket.set(categoryData, 30, TimeUnit.DAYS);
        return categoryData;
      } else {
        log.info("获取文章数据【book={}】 失败", yuqueName);
        return new YuqueCategoryData();
      }

    } finally {
      if (lock.isLocked()) {
        lock.unlock();
      }
      log.info("释放锁【{}】 完成", lockKey);
    }
  }

  @Override
  public Optional<BookCategory> findById(Long categoryId) {
    return Optional.ofNullable(categoryTunnel.findById(categoryId)).map(converter::converterFrom);
  }
}
