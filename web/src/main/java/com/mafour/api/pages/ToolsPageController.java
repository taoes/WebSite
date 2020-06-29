package com.mafour.api.pages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
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

  @GetMapping("/converter.html")
  public String toConverter() {
    return "tools/converter";
  }

  @GetMapping("/jsonFormat.html")
  public String toJsonFormatPage() {
    return "tools/json";
  }
}
