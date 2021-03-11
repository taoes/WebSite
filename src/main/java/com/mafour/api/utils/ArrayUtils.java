package com.mafour.api.utils;

public class ArrayUtils {

  /** 判断数组是否是空或者空集 */
  public static boolean isEmpty(Object[] array) {
    return array == null || array.length == 0;
  }
}
