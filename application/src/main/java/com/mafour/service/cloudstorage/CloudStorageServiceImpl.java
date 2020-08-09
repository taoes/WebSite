package com.mafour.service.cloudstorage;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CloudStorageServiceImpl implements CloudStorageService {

  private final Configuration configuration;

  private final UploadManager manager;

  public CloudStorageServiceImpl() {
    configuration = new Configuration(Region.region0());
    manager = new UploadManager(configuration);
  }

  @Override
  public void upload(String fileName, byte[] data) {

    Auth auth = Auth.create("", "");
    String token = auth.uploadToken("bucket");

    try {
      Response response = manager.put(data, fileName, token);

    } catch (QiniuException e) {
      log.error("上传文件到七牛云失败", e);
    }
  }

  @Override
  public boolean exist(String fileName) {
    return false;
  }
}
