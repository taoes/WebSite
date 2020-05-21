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
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class IndexPageController {

  private final SystemService systemService;

  private final BookService bookService;

  private final GithubService githubService;

  /** 系统主页 */
  @GetMapping
  public String indexPage(Model model, HttpServletRequest request) {
    String queryString = request.getQueryString();
    if (queryString != null && queryString.length() > 0) {
      throw new NotFoundException("页面资源不存在，" + request.getRequestURL());
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
