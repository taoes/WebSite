package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.service.book.BookCategoryService;
import com.mafour.service.book.BookContentService;
import com.mafour.service.book.BookService;
import com.mafour.service.book.bean.Book;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.system.SystemService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/page/book")
public class BookPageController {

  private BookService bookService;

  private BookCategoryService categoryService;

  private BookContentService contentService;

  private SystemService systemService;

  private final String PIC_PREFIX = "https://cdn.nlark.com/";

  @GetMapping("/{bookId}")
  public String toBlogPage(@PathVariable("bookId") Long bookId, Model model) {

    // 查询书籍信息
    Optional<Book> bookOptional = bookService.find(bookId);
    if (!bookOptional.isPresent()) {
      model.addAttribute("msg", "图书信息不存在");
      return "base/404";
    }

    // 查询目录
    Book book = bookOptional.get();
    YuqueCategoryData categories = categoryService.findByBook(book.getLinkUrl());

    // 查询配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    model.addAttribute("categoryList", categories);
    model.addAttribute("config", configMap);
    model.addAttribute("book", book);

    return "book/category";
  }

  @GetMapping("/{bookName}/category/{slug}")
  public String toBookContent(
      @PathVariable("bookName") String bookName,
      @PathVariable("slug") String slug,
      HttpServletRequest request,
      Model model) {

    // 尝试获取文章信息
    YuqueDoc yuqueDoc = contentService.findByCategoryId(bookName, slug);
    YuqueDoc.Data data = yuqueDoc.getData();

    String host = request.getHeader("Host");
    String contentStr;

    if (StringUtils.hasText(host)) {
      contentStr = data.getBody_html().replaceAll(PIC_PREFIX, "https://" + host + "/picture?param=");
    } else {
      contentStr = Optional.ofNullable(yuqueDoc.getData().getBody_html()).orElse("暂无内容");
    }

    // 查询系统配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    List<Book> allBook = bookService.findAllBook();

    model.addAttribute("content", contentStr);
    model.addAttribute("config", configMap);
    model.addAttribute("slug", slug);
    model.addAttribute("title", data.getTitle());
    model.addAttribute("bookName", bookName);
    model.addAttribute("bookList", allBook);
    return "book/content";
  }
}
