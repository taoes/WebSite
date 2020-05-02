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
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

  private BookCategoryTunnel categoryTunnel;

  private RedissonClient redissonClient;

  private BookCategoryConverter converter;

  final OkHttpClient client = new OkHttpClient();

  @SneakyThrows
  @Override
  public YuqueCategoryData findByBook(String yuqueName) {
    log.info("find info from redis: book = {}", yuqueName);
    String cacheKey = "CATEGORY:" + yuqueName;
    RBucket<YuqueCategoryData> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      log.info("find info from cache :{}", cacheKey);
      return bucket.get();
    }

    log.info("not found cache : {},start send http request", cacheKey);

    Request request =
        new Request.Builder()
            .url("https://www.yuque.com/api/v2/repos/zhoutao123/" + yuqueName + "/toc")
            .addHeader("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX")
            .addHeader("User-Agent", "www.zhoutao123.com")
            .addHeader("Content-Type", "application/json")
            .build();

    Response execute = client.newCall(request).execute();
    YuqueCategoryData categoryData = null;
    if (execute.isSuccessful()) {
      String string = Optional.ofNullable(execute.body().string()).orElse("{}");
      log.info("get from web book :{} category complete", yuqueName);
      categoryData = JSON.parseObject(string, YuqueCategoryData.class);
    } else {
      log.info("get from web book :{} category is fail", yuqueName);
      categoryData = new YuqueCategoryData();
    }

    // 保存缓存记录
    bucket.set(categoryData, 24, TimeUnit.HOURS);
    return categoryData;
  }

  @Override
  public Optional<BookCategory> findById(Long categoryId) {
    return Optional.ofNullable(categoryTunnel.findById(categoryId)).map(converter::converterFrom);
  }
}
