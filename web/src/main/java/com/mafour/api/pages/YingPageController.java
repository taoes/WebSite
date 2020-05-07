package com.mafour.api.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/ying")
public class YingPageController {

  @GetMapping
  public String toBookContent() {
    return "ying/ying";
  }
}
