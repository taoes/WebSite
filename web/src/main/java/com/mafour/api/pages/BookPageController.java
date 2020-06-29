package com.mafour.api.pages;

import com.mafour.common.SystemConfigKey;
import com.mafour.dao.BookUpdateRecordDO;
import com.mafour.service.SeoService;
import com.mafour.service.book.BookCategoryService;
import com.mafour.service.book.BookContentService;
import com.mafour.service.book.BookService;
import com.mafour.service.book.bean.Book;
import com.mafour.service.book.yuque.YuqueCategory;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.comment.Comment;
import com.mafour.service.comment.CommentService;
import com.mafour.service.system.SystemService;
import com.mafour.tunnel.BookUpdateRecordTunnel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

  private final BookContentService contentService;

  private final SystemService systemService;

  private final CommentService commentService;

  private final SeoService seoService;

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
    YuqueDoc.Data data = yuqueDoc.getData();
    String host = request.getHeader("Host");
    String contentStr;

    if (StringUtils.hasText(host)) {
      contentStr = data.getBody_html().replaceAll(PIC_PREFIX, "http://" + host + "/picture?param=");
    } else {
      contentStr = Optional.ofNullable(yuqueDoc.getData().getBody_html()).orElse("暂无内容");
    }

    YuqueDoc.Book book = data.getBook();
    String bookNameOfCN = book.getName();

    Map<String, String> configMap = systemService.getByKeys(SystemConfigKey.indexKey());
    Long bookId = bookService.findByName(bookName).map(Book::getId).orElse(0L);
    List<Comment> comments = commentService.all(bookName, slug);
    seoService.push(bookName, slug);

    model.addAttribute("content", contentStr);
    model.addAttribute("config", configMap);
    model.addAttribute("slug", slug);
    model.addAttribute("title", data.getTitle());
    model.addAttribute("count", yuqueDoc.getCount());
    model.addAttribute("desc", data.getDescription());
    model.addAttribute("bookName", bookName);
    model.addAttribute("bookId", bookId);
    model.addAttribute("bookNameOfCN", bookNameOfCN);
    model.addAttribute("comments", comments);

    return "book/content";
  }
}
