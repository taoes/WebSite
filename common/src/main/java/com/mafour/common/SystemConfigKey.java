package com.mafour.common;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SystemConfigKey {
  INDEX_IMG("首页图片"),
  WEB_GRAY("网站全灰"),
  WEB_RECORD("网站备案信息");

  private java.lang.String desc;

  private static Set<SystemConfigKey> indexKey;

  static {
    indexKey = new HashSet<>();
    indexKey.add(INDEX_IMG);
    indexKey.add(WEB_GRAY);
    indexKey.add(WEB_RECORD);
  }

  // 首页Key
  public static Set<SystemConfigKey> indexKey() {
    return indexKey;
  }
}