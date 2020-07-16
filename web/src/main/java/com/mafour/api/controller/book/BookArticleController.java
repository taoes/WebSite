package com.mafour.api.controller.book;

import com.mafour.common.Response;
import com.mafour.service.book.BookArticleServiceImpl;
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

  private final BookArticleServiceImpl articleService;

  @GetMapping("/recommend")
  public Response<Void> setSlugRecommend(
      @RequestParam() String slug,
      @RequestParam(required = false, defaultValue = "true") boolean set) {
    articleService.recommend(true, slug);
    return Response.ok();
  }
}
