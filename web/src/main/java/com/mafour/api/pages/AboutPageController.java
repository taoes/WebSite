package com.mafour.api.pages;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPageController {

  private final HttpSession session;

  public AboutPageController(HttpSession session) {
    this.session = session;
  }

  @GetMapping("/page/about.html")
  public String aboutPage(Model model) {
    return "page/about";
  }
}
