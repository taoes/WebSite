package com.mafour.api.pages.module;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/blog")
public class BlogPageController {

  @GetMapping
  public String toBlogPage(Model model) {
    // 查询博客首页数据
    return "blog/index";
  }
}
