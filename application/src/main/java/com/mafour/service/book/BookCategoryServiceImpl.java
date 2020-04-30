package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.service.book.bean.BookCategory;
import com.mafour.service.book.converter.BookCategoryConverter;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.tunnel.BookCategoryTunnel;
import com.mafour.tunnel.BookContentTunnel;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

  private BookCategoryTunnel categoryTunnel;

  private BookContentTunnel bookContentTunnel;

  private BookCategoryConverter converter;

  final OkHttpClient client = new OkHttpClient();

  @SneakyThrows
  @Override
  public YuqueCategoryData findByBook(String yuqueName) {
    log.info("查询知识库:{}的目录信息", yuqueName);
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
      log.info("查询知识库:{}的目录信息完成", yuqueName);
      return JSON.parseObject(string, YuqueCategoryData.class);
    } else {
      log.info("查询知识库:{}的目录信息失败", yuqueName);
      return new YuqueCategoryData();
    }
  }

  @Override
  public Optional<BookCategory> findById(Long categoryId) {
    return Optional.ofNullable(categoryTunnel.findById(categoryId)).map(converter::converterFrom);
  }
}
