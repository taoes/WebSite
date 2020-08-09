package com.mafour.service.cloudstorage;

public interface CloudStorageService {

  void upload(String fileName, byte[] data);

  /** 检查文件是否存在 */
  boolean exist(String fileName);
}
