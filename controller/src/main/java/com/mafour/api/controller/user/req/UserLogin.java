package com.mafour.api.controller.user.req;

import lombok.Data;

@Data
public class UserLogin {

  private String username;

  private String password;
}
