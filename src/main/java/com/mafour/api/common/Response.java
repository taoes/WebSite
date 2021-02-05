package com.mafour.api.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {

  private String code;

  private T data;

  public static <T> Response<T> ok(T t) {
    return new Response<T>().setCode("SUCCESS").setData(t);
  }

  public static <T> Response<T> ok() {
    return new Response<T>().setCode("SUCCESS");
  }
}
