package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.service.book.BookArticleService;
import com.mafour.service.book.BookCategoryService;
import com.mafour.service.book.bean.Book;
import com.mafour.service.book.bean.BookArticle;
import com.mafour.service.book.yuque.YuqueCategoryData;
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

  private final BookCategoryService categoryService;

  private final BookArticleService articleService;

  @GetMapping("/page/recommend")
  public String recommendList(Model model) {

    // 查询推荐文章的目录
    List<BookArticle> articles = articleService.recommendList();

    // 查询配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    model.addAttribute("articles", articles);
    model.addAttribute("config", configMap);

    return "book/recommend";
  }

  @GetMapping("/page/blog")
  public String blogPage(Model model) {
    // 查询目录
    Book book = new Book().setId(0L).setLinkUrl("blog").setTitle("我的博客");
    YuqueCategoryData categories = categoryService.findByBook(book.getLinkUrl());

    // 查询配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    model.addAttribute("categoryList", categories);
    model.addAttribute("config", configMap);
    model.addAttribute("book", book);

    return "book/category";
  }
}
