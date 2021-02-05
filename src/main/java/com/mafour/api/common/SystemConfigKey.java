package com.mafour.api.common;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SystemConfigKey {
  INDEX_IMG("首页图片"),
  WEB_GRAY("网站全灰"),
  CONTENT_IMG("内容背景"),
  WEB_RECORD("网站备案信息"),
  BAIDU_PUSH("开启百度推送");

  private final String desc;

  private static final Set<SystemConfigKey> indexKey;

  static {
    indexKey = new HashSet<>();
    indexKey.add(INDEX_IMG);
    indexKey.add(WEB_GRAY);
    indexKey.add(WEB_RECORD);
    indexKey.add(BAIDU_PUSH);
    indexKey.add(CONTENT_IMG);
  }

  // 首页Key
  public static Set<SystemConfigKey> indexKey() {
    return indexKey;
  }
}
