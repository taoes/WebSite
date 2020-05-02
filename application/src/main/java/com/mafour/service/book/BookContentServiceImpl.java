package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.dao.book.BookContentDO;
import com.mafour.exception.NotFoundException;
import com.mafour.service.book.bean.BookContent;
import com.mafour.service.book.converter.BookContentConverter;
import com.mafour.service.book.yuque.YuqueDoc;
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
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookContentServiceImpl implements BookContentService {

  private BookContentTunnel contentTunnel;

  private BookContentConverter converter;

  private RedissonClient redissonClient;

  final OkHttpClient client = new OkHttpClient();

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
    log.info("find book :{} slug ：{} info from cache", bookName, slug);

    String cacheKey = "CATEGORY:" + bookName + ":CONTENT:" + slug;
    RBucket<YuqueDoc> bucket = redissonClient.getBucket(cacheKey);
    if (bucket.isExists()) {
      log.info("find cache,skip http request");
      return bucket.get();
    }

    log.info(" not find cache,start http request...");
    Request request =
        new Request.Builder()
            .url("https://www.yuque.com/api/v2/repos/zhoutao123/" + bookName + "/docs/" + slug)
            .addHeader("X-Auth-Token", "vdeXXCOyctZhx9CqPIMUMCTPwb6voFM4OcxVYssX")
            .addHeader("User-Agent", "www.zhoutao123.com")
            .addHeader("Content-Type", "application/json")
            .build();

    Response execute = client.newCall(request).execute();
    YuqueDoc yuqueDoc = null;
    if (execute.isSuccessful()) {
      String string = Optional.ofNullable(execute.body().string()).orElse("{}");
      log.info("get from web  book = {} slug = {} is complete", bookName, slug);
      yuqueDoc = JSON.parseObject(string, YuqueDoc.class);
    } else {
      log.info("get from web book = {} slug = {} is fail", bookName, slug);
      yuqueDoc = new YuqueDoc();
    }

    // 保存缓存记录
    bucket.set(yuqueDoc, 2, TimeUnit.HOURS);
    return yuqueDoc;
  }
}
