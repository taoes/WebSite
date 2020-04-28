package com.mafour.api.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

  @GetMapping("/test")
  public String test() {
    return UUID.randomUUID().toString();
  }
}
