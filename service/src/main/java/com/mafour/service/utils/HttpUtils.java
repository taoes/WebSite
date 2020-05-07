package com.mafour.service.utils;

import static java.lang.String.format;

import com.alibaba.fastjson.JSON;
import com.mafour.service.github.GithubUser;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

  public static final String client = "4b3f1745967d3a9d09d0";
  public static final String sec = "946a321dfc1ca558e5ec0c43eea7a59bda72bf60";

  private static CloseableHttpClient httpClient;

  private static final RequestConfig requestConfig =
      RequestConfig.custom()
          .setSocketTimeout(5000)
          .setConnectTimeout(5000)
          .setConnectionRequestTimeout(5000)
          .build();

  static {
    PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
    connManager.setMaxTotal(300);
    connManager.setDefaultMaxPerRoute(300);
    httpClient = HttpClients.custom().setConnectionManager(connManager).build();
  }

  public static GithubUser getGithubUserByCode(String code) {
    String url =
        format(
            "https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s",
            client, sec, code);

    String post = post(url);
    String accessToken = post.split("&")[0].split("=")[1];
    String jsonStr =
        get(format("https://api.github.com/user?access_token=%s", accessToken), new HashMap<>(0));
    return JSON.parseObject(jsonStr, GithubUser.class);
  }

  public static String post(String url) {
    HttpPost httpPost = new HttpPost(url);
    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
      if (response == null) {
        return null;
      }
      return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  @SneakyThrows
  public static String get(String url, Map<String, String> header) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    // 发送了一个http请求
    CloseableHttpResponse response = httpclient.execute(httpGet);
    // 如果响应200成功,解析响应结果
    if (response.getStatusLine().getStatusCode() == 200) {
      // 获取响应的内容
      HttpEntity responseEntity = response.getEntity();

      return EntityUtils.toString(responseEntity);
    }
    return null;
  }
}
