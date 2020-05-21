package com.mafour.api.pages;

import com.mafour.service.link.Link;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinkPageController {

  @GetMapping("/page/link.html")
  public String aboutPage(Model model) {
    Map<String, List<Link>> linkMap = new HashMap<>(0);
    List<Link> links = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      links.add(Link.newLink());
    }

    linkMap.put("友情链接", links);
    linkMap.put("小工具", links);
    linkMap.put("开发必备", links);
    linkMap.put("前端工具", links);
    linkMap.put("数据库工具", links);

    model.addAttribute("linkMap", linkMap);
    return "page/link";
  }
}
