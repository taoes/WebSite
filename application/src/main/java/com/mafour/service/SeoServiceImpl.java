package com.mafour.service;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SeoServiceImpl implements SeoService {

  private static final OkHttpClient client = new OkHttpClient();

  @Async
  @Override
  public void push(String bookName, String slugName) throws IOException {

    // 推送数据到百度
    String url = "https://www.zhoutao123.com/page/book/" + bookName + "/category/" + slugName;
    pushToBaidu(url);
  }

  @Async
  @Override
  public void push(String bookId) throws IOException {
    // 推送数据到百度
    String url = "https://www.zhoutao123.com/page/book/" + bookId;
    pushToBaidu(url);
  }

  private void pushToBaidu(String url) throws IOException {
    final MediaType JSON = MediaType.parse("text/plain; charset=utf-8");
    okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, url);

    Request request1 =
        new Request.Builder()
            .url(
                "http://data.zz.baidu.com/urls?site=https://www.zhoutao123.com&token=32trKBucZgw6WyK1")
            .post(body)
            .build();
    Response response = client.newCall(request1).execute();
    if (response.isSuccessful()) {
      log.info("推送:{} 到Baidu的请求结果:{}", url, response.body().string());
    } else {
      log.info("推送:{} 请求失败", url);
    }
  }
}
