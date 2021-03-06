package com.mafour.api.controller.book;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

  /**
   * 重定向文件图片访问，代理到语雀的文件
   *
   * <p>https://cdn.nlark.com/yuque0/2020/png/437981/1583756278116-3aab6997-6012-4a2a-bf38-c4f8652a1831.png
   */
  @GetMapping
  @SneakyThrows
  public void getPic(String param, HttpServletResponse response) {
    // TODO: 从数据库中查询图片是否已经缓存到七牛云中，是的话直接返回七牛云地址
    // TODO: 否则从云端获取数据
    try (InputStream inStream = findPictureDataFromRemote(param, response);
        OutputStream os = response.getOutputStream()) {
      byte[] data = readInputStream(inStream);
      inStream.read(data);
      os.write(data);
      os.flush();
    } catch (Exception e) {
      log.info("获取图片文件信息失败:{}", e.getMessage());
    }
  }

  /**
   * 从语雀接口中获取图片数据
   *
   * @return 返回文件流 & 设置相应的内容类型头
   */
  @SneakyThrows
  private InputStream findPictureDataFromRemote(String param, HttpServletResponse response) {
    URL url = new URL(String.format("https://cdn.nlark.com/%s", param));
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setConnectTimeout(10_000);
    InputStream inputStream = conn.getInputStream(); // 通过输入流获取图片数据

    // 获取文件响应头
    List<String> contentType = conn.getHeaderFields().get("Content-Type");
    String contentTypeStr = Strings.EMPTY;
    if (!CollectionUtils.isEmpty(contentType)) {
      contentTypeStr = contentType.get(0);
    }
    response.setContentType(contentTypeStr);
    return inputStream;
  }

  private static byte[] readInputStream(InputStream inStream) throws Exception {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[2048];
    int len = 0;
    while ((len = inStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, len);
    }
    return outStream.toByteArray();
  }

  private void uploadToCloud(){}
}
