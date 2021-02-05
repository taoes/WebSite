package com.mafour.api.utils;

public class StringUtils {

  /** 将字节数组转换为十六进制字符串 */
  public static String byteToStr(byte[] byteArray) {
    StringBuilder strDigest = new StringBuilder();
    for (byte b : byteArray) {
      strDigest.append(byteToHexStr(b));
    }
    return strDigest.toString();
  }
  /** 将字节转换为十六进制字符串 */
  private static String byteToHexStr(byte mByte) {
    char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    char[] tempArr = new char[2];
    tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
    tempArr[1] = Digit[mByte & 0X0F];
    return new String(tempArr);
  }
}
