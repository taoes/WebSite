package com.mafour.service.ding;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NetUtils {

  private static final RestTemplate restTemplate = new RestTemplate();

  public static void sendDingMsg(String token, String content) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    HttpEntity<String> request = new HttpEntity<>(content, headers);

    String url = "https://oapi.dingtalk.com/robot/send?access_token=" + token;
    ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);

    String body = postForEntity.getBody();

    System.out.println(body);
  }
}
