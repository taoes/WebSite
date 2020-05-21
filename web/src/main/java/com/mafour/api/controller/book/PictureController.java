package com.mafour.api.controller.book;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

  //
  // https://cdn.nlark.com/yuque0/2020/png/437981/1583756278116-3aab6997-6012-4a2a-bf38-c4f8652a1831.png

  @GetMapping
  public void getPic(String param, HttpServletResponse response) {

    try {
      URL url = new URL("https://cdn.nlark.com/" + param);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(5 * 1000);
      InputStream inStream = conn.getInputStream(); // 通过输入流获取图片数据

      List<String> contentType = conn.getHeaderFields().get("Content-Type");
      String contentTypeStr = "";
      if (!CollectionUtils.isEmpty(contentType)) {
        contentTypeStr = contentType.get(0);
      }

      byte[] data = readInputStream(inStream);
      int read = inStream.read(data);
      inStream.close();
      response.setContentType(contentTypeStr); // 设置返回的文件类型
      OutputStream os = response.getOutputStream();
      os.write(data);
      os.flush();
      os.close();
    } catch (Exception e) {
      log.info("获取图片文件信息失败:{}", e.getMessage());
    }
  }

  public static byte[] readInputStream(InputStream inStream) throws Exception {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[2048];
    int len = 0;
    while ((len = inStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, len);
    }
    return outStream.toByteArray();
  }
}
