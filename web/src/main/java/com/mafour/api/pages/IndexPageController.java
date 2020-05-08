package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.exception.NotFoundException;
import com.mafour.service.book.BookService;
import com.mafour.service.book.bean.Book;
import com.mafour.service.github.Github;
import com.mafour.service.github.GithubService;
import com.mafour.service.system.SystemService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class IndexPageController {

  private SystemService systemService;

  private BookService bookService;

  private GithubService githubService;

  /** 系统主页 */
  @GetMapping
  public java.lang.String indexPage(Model model, String p) {
    if (p != null) {
      throw new NotFoundException();
    }

    List<Book> bookList = bookService.findAllBook();
    List<Github> githubList = githubService.findAll();

    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    model.addAttribute("config", configMap);
    model.addAttribute("bookList", bookList);
    model.addAttribute("githubList", githubList);
    return "index";
  }

  /** 子模块页面 */
  @GetMapping("/page/{moduleName}/123")
  public java.lang.String blogPage(@PathVariable("moduleName") java.lang.String moduleName) {
    return "page/" + moduleName;
  }
}
