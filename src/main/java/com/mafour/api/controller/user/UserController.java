package com.mafour.api.controller.user;

import com.mafour.api.service.user.UserService;
import com.mafour.api.service.user.model.JwtToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public JwtToken test() {
    return userService.login("", "");
  }
}
