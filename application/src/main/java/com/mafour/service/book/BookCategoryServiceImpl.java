package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.dao.book.BookCategoryDO;
import com.mafour.service.book.bean.BookCategory;
import com.mafour.service.book.converter.BookCategoryConverter;
import com.mafour.service.book.yuque.YuqueCategory;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.tunnel.BookCategoryTunnel;
import java.util.ArrayList;
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
import org.springframework.beans.BeanUtils;
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
  public YuqueCategoryData findByBook(String book) {
    // 从缓存中获取目录信息
    String cacheKey = "WEB:CATEGORY:" + book;
    RBucket<YuqueCategoryData> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      return bucket.get();
    }

    // 缓存不存在，从语雀API中查询
    String lockKey = "WEB:LOCK:" + cacheKey;
    RLock lock = redissonClient.getLock(lockKey);
    try {
      lock.lock(10, TimeUnit.SECONDS);
      bucket = redissonClient.getBucket(cacheKey);
      if (bucket.isExists()) {
        return bucket.get();
      }
      YuqueCategoryData data = findByYuqueApi(book);
      bucket.set(data, data.getData() == null ? 1 : 30, TimeUnit.DAYS);
      return data;
    } finally {
      if (lock.isLocked()) {
        lock.unlock();
      }
    }
  }

  @SneakyThrows
  private YuqueCategoryData findByYuqueApi(String yuqueName) {
    Request request =
        new Request.Builder()
            .url("https://www.yuque.com/api/v2/repos/zhoutao123/" + yuqueName + "/toc")
            .addHeader("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX")
            .addHeader("User-Agent", "www.zhoutao123.com")
            .addHeader("Content-Type", "application/json")
            .build();

    try (Response execute = client.newCall(request).execute()) {

      if (execute.isSuccessful()) {
        String string = Optional.ofNullable(execute.body().string()).orElse("{}");
        YuqueCategoryData categoryData = JSON.parseObject(string, YuqueCategoryData.class);
        save(yuqueName, categoryData);
        return categoryData;
      } else {
        log.info("获取文章数据【book={}】 失败", yuqueName);
        return new YuqueCategoryData();
      }
    }
  }

  private void save(String yuqueName, YuqueCategoryData categoryData) {
    List<YuqueCategory> categoryList = categoryData.getData();
    List<BookCategory> bookCategoryList = new ArrayList<>();
    int i = 0;
    for (YuqueCategory category : categoryList) {
      BookCategory bookCategory = new BookCategory();
      BeanUtils.copyProperties(category, bookCategory);
      bookCategory.setBookName(yuqueName);
      bookCategory.setSequence(++i);
      bookCategoryList.add(bookCategory);
    }

    List<BookCategoryDO> categoryDOList =
        bookCategoryList.stream().map(converter::converterTo).collect(Collectors.toList());
    categoryTunnel.cleanAndSaveBatch(yuqueName, categoryDOList);
  }
}
