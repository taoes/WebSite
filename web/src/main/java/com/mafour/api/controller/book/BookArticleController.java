package com.mafour.api.controller.book;

import com.mafour.common.Response;
import com.mafour.service.book.BookArticleService;
import com.mafour.service.book.BookArticleStartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/book_article")
public class BookArticleController {

  private final BookArticleStartService startService;

  private final BookArticleService articleService;

  @GetMapping("/recommend")
  public Response<Void> setSlugRecommend(
      @RequestParam() String slug,
      @RequestParam(required = false, defaultValue = "true") boolean set) {
    articleService.recommend(true, slug);
    return Response.ok();
  }

  @GetMapping("/start")
  public Response<Integer> stat(
      @RequestParam String book, @RequestParam String slug, @RequestParam int value) {
    Integer stat = startService.createStart(book, slug, value);
    return Response.ok(stat);
  }
}
