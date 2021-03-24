package com.mafour.api.service.seo;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SeoServiceImpl implements SeoService {

  private static final String WEB = "https://www.zhoutao123.com";

  private static final String WEB_PREFIX = WEB + "/page/book/";

  private static final OkHttpClient client = new OkHttpClient();

  private static final String SEO_TOKEN = "32trKBucZgw6WyK1";

  @Async
  @Override
  public void push(String bookName, String slugName) {
    String url = WEB_PREFIX + bookName + "/category/" + slugName;
    pushToBaidu(url);
  }

  @Async
  public void push(String bookId) {
    String url = WEB_PREFIX + bookId;
    pushToBaidu(url);
  }

  private void pushToBaidu(String url) {
    final MediaType JSON = MediaType.parse("text/plain; charset=utf-8");
    okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, url);
    String seoUrl = String.format("http://data.zz.baidu.com/urls?site=%s&token=%s", WEB, SEO_TOKEN);
    Request request = new Request.Builder().url(seoUrl).post(body).build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        assert response.body() != null;
        log.info("推送:{} 请求失败", url);
      }
      log.info("推送:{} 到百度站长完成,响应:{}", url, response.body().string());
    } catch (IOException e) {
      log.info("推送SEO请求失败", e);
    }
  }
}
