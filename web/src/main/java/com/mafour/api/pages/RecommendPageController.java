package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.service.book.BookArticleService;
import com.mafour.service.book.bean.BookArticle;
import com.mafour.service.recommend.Recommend;
import com.mafour.service.recommend.RecommendService;
import com.mafour.service.system.SystemService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class RecommendPageController {

  @Autowired private final SystemService systemService;

  @Autowired private final RecommendService recommendService;

  @Autowired private final BookArticleService articleService;

  @GetMapping("/page/recommend")
  public String recommendList(Model model) {

    // 查询推荐文章的目录
    List<BookArticle> articles = articleService.recommendList();

    // 查询配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    // 获取其他推荐
    List<Recommend> recommends = recommendService.listAll();
    Map<String, List<Recommend>> recommendGroup =
        recommends.stream().collect(Collectors.groupingBy(Recommend::getType));

    model.addAttribute("articles", articles);
    model.addAttribute("recommendGroup", recommendGroup);
    model.addAttribute("config", configMap);

    return "book/recommend";
  }
}
