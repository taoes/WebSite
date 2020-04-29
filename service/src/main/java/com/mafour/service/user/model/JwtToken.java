package com.mafour.service.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtToken {

  private String token;

  public static JwtToken valueOf(String token) {
    return new JwtToken(token);
  }
}
