package com.mafour.api.pages;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexPageController {

  private final HttpSession session;

  public IndexPageController(HttpSession session) {
    this.session = session;
  }

  @GetMapping("/")
  public String indexPage(Model model) {
    model.addAttribute("name", "张思睿");
    Integer count =
        Optional.ofNullable(session.getAttribute("count"))
            .map(Object::toString)
            .map(Integer::parseInt)
            .orElse(0);

    session.setAttribute("count", ++count);
    session.setAttribute("name", "张思睿");
    return "index";
  }

  @GetMapping("/page/{moduleName}")
  public String blogPage(@PathVariable("moduleName") String moduleName) {
    return moduleName;
  }

  @GetMapping("/page/example")
  public String examplePage(Model model) {
    return "example";
  }
}
