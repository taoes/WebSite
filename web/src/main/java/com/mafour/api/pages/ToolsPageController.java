package com.mafour.api.pages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/page/tools")
public class ToolsPageController {

  @GetMapping("/no.html")
  public String toNoCompletePage() {
    return "tools/no";
  }
}
