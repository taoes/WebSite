package com.mafour.service.book;

import com.alibaba.fastjson.JSON;
import com.mafour.dao.book.BookContentDO;
import com.mafour.service.book.bean.BookContent;
import com.mafour.service.book.converter.BookContentConverter;
import com.mafour.service.book.yuque.YuqueDoc;
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
public class BookContentServiceImpl implements BookContentService {

  private BookContentTunnel contentTunnel;

  private BookContentConverter converter;

  final OkHttpClient client = new OkHttpClient();

  @Override
  public BookContent findByCategoryId(Long categoryId) {
    BookContentDO categoryDO = contentTunnel.getByCategoryId(categoryId);
    if (categoryDO == null) {
      throw new RuntimeException("未发现文章内容");
    }
    return converter.converterFrom(categoryDO);
  }

  @SneakyThrows
  @Override
  public YuqueDoc findByCategoryId(String bookName, String slug) {
    log.info("查询知识库:{}的文章：{} 信息", bookName, slug);
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
      log.info("查询知识库:{}的文章：{} 信息完成", bookName, slug);
      return JSON.parseObject(string, YuqueDoc.class);
    } else {
      log.info("查询知识库:{}的文章：{} 信息失败", bookName, slug);
      return new YuqueDoc();
    }
  }
}
