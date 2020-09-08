package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.exception.NotFoundException;
import com.mafour.service.SeoService;
import com.mafour.service.book.BookArticleService;
import com.mafour.service.book.BookArticleStartService;
import com.mafour.service.book.BookCategoryService;
import com.mafour.service.book.BookService;
import com.mafour.service.book.bean.Book;
import com.mafour.service.book.yuque.YuqueCategory;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.comment.Comment;
import com.mafour.service.comment.CommentService;
import com.mafour.service.github.Github;
import com.mafour.service.github.GithubService;
import com.mafour.service.system.SystemService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/page/book")
public class BookPageController {

  private final BookService bookService;

  private final BookCategoryService categoryService;

  private final BookArticleService contentService;

  private final SystemService systemService;

  private final CommentService commentService;

  private final SeoService seoService;

  private final GithubService githubService;

  private final BookArticleStartService startService;

  private static final String PIC_PREFIX = "https://cdn.nlark.com/";

  @GetMapping("/{bookId}")
  public String toBlogPage(@PathVariable("bookId") Long bookId, Model model) throws IOException {
    Optional<Book> bookOptional = bookService.find(bookId);
    if (!bookOptional.isPresent()) {
      model.addAttribute("msg", "图书信息不存在");
      return "base/404";
    }

    seoService.push(bookId.toString());

    // 查询目录
    Book book = bookOptional.get();
    YuqueCategoryData categories = categoryService.findByBook(book.getLinkUrl());

    // 查询配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    // 生成描述
    String desc;
    if (CollectionUtils.isEmpty(categories.getData())) {
      desc = book.getTitle();
    } else {
      List<String> categoryTitles =
          categories.getData().stream().map(YuqueCategory::getTitle).collect(Collectors.toList());
      desc = Strings.join(categoryTitles, ',');
    }

    model.addAttribute("categoryList", categories);
    model.addAttribute("config", configMap);
    model.addAttribute("book", book);
    model.addAttribute("desc", desc);

    return "book/category";
  }

  @GetMapping("/{bookName}/category/{slug}")
  public String toBookContent(
      @PathVariable("bookName") String bookName,
      @PathVariable("slug") String slug,
      HttpServletRequest request,
      Model model)
      throws IOException {

    // 尝试获取文章信息
    YuqueDoc yuqueDoc = contentService.findByCategoryId(bookName, slug);

    // 处理文章信息
    YuqueDoc.Data data = yuqueDoc.getData();
    String host = request.getHeader("Host");
    String contentStr;
    if (StringUtils.hasText(data.getBody_html())) {
      contentStr = data.getBody_html().replaceAll(PIC_PREFIX, "http://" + host + "/picture?param=");
    } else {
      contentStr =
          Optional.ofNullable(yuqueDoc.getData().getBody_html())
              .orElse("<h1 style='font-size:28px'>暂无内容,正在准备中，敬请期待...</h1>");
    }

    // 读取系统配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());
    Long bookId = bookService.findByName(bookName).map(Book::getId).orElse(0L);

    // 获取评论
    List<Comment> comments = commentService.all(bookName, slug);

    // 异步推送SEO
    seoService.push(bookName, slug);

    // 获取SEO关键词
    String searchKey = contentService.findSearchKey(slug);

    // 获取目录
    List<YuqueCategory> categoryList = categoryService.findByBook(bookName).getData();

    // 获取评级
    Integer start = startService.getStart(bookName, slug);

    model.addAttribute("content", contentStr);
    model.addAttribute("config", configMap);
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("slug", slug);
    model.addAttribute("data", data);
    model.addAttribute("count", yuqueDoc.getCount());
    model.addAttribute("searchKey", searchKey);
    model.addAttribute("bookName", bookName);
    model.addAttribute("bookId", bookId);
    model.addAttribute("comments", comments);
    model.addAttribute("start", start);

    return "book/content";
  }

  @GetMapping("/more/list")
  public String indexPage(Model model, HttpServletRequest request) {
    String queryString = request.getQueryString();
    if (queryString != null && queryString.length() > 0) {
      throw new NotFoundException("页面资源不存在，" + request.getRequestURL());
    }

    // 获取图书列表
    List<Book> bookList = bookService.findAllBook();

    // 获取Github列表
    List<Github> githubList = githubService.findAll();

    // 获取系统配置
    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());

    model.addAttribute("config", configMap);
    model.addAttribute("bookList", bookList);
    model.addAttribute("githubList", githubList);
    return "bookList";
  }
}
