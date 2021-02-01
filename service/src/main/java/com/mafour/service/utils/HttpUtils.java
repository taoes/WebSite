package com.mafour.service.utils;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {

  private static final OkHttpClient okHttpClient = new OkHttpClient();

  public static <T> T post(String url, Map<String, String> headers, Class<T> clazz) {
    Request.Builder requestBuilder = new Request.Builder().url(url);
    if (headers != null && headers.size() > 0) {
      for (Entry<String, String> entry : headers.entrySet()) {
        requestBuilder.addHeader(entry.getKey(), entry.getValue());
      }
    }
    try (Response execute = okHttpClient.newCall(requestBuilder.build()).execute()) {
      if (execute.isSuccessful()) {
        String string = Optional.ofNullable(execute.body().string()).orElse("{}");
        return JSON.parseObject(string, clazz);
      } else {
        return null;
      }
    } catch (IOException e) {
      return null;
    }
  }
}
