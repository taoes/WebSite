package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.service.book.BookArticleService;
import com.mafour.service.book.bean.BookArticle;
import com.mafour.service.system.SystemService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BlogPageController {

  private final SystemService systemService;

  private final BookArticleService articleService;

  @GetMapping("/page/recommend")
  public String blogPage(Model model) {

    // 查询推荐文章的目录
    List<BookArticle> articles = articleService.recommendList();

    // 查询配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    model.addAttribute("articles", articles);
    model.addAttribute("config", configMap);

    return "book/recommend";
  }
}
