package com.mafour.api.controller.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mafour.api.common.Response;
import com.mafour.api.service.book.BookArticleService;
import com.mafour.api.service.book.BookArticleStartService;
import com.mafour.api.service.book.bean.BookUpdateRecord;
import com.mafour.api.service.redis.RedisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 文章评论以及文章评级 */
@RestController
@RequestMapping("/book_article")
public class BookArticleController {

  @Autowired private BookArticleStartService startService;

  @Autowired private BookArticleService articleService;

  @Autowired private RedisService redisService;

  private static final String LATEST_CACHE_KEY_TEMP = "WEB:PUBLISH:LATEST:%s";

  @GetMapping("/recommend")
  public Response<Void> setSlugRecommend(@RequestParam() String slug) {
    articleService.recommend(true, slug);
    return Response.ok();
  }

  @GetMapping("/start")
  public Response<Integer> stat(
      @RequestParam String book, @RequestParam String slug, @RequestParam int value) {
    Integer stat = startService.createStart(book, slug, value);
    return Response.ok(stat);
  }

  /**
   * 获取最新的 发布 记录
   *
   * @return 返回最新的发布的文章记录
   */
  @GetMapping("/record")
  public List<BookUpdateRecord> findLatestArticle(@RequestParam(defaultValue = "10") int limit) {
    String cacheKey = String.format(LATEST_CACHE_KEY_TEMP, limit);
    List<BookUpdateRecord> records;
    if (redisService.hasKey(cacheKey)) {
      return redisService.find(cacheKey, new TypeReference<List<BookUpdateRecord>>() {});
    } else {
      records = articleService.findLatestPublish(limit);
      redisService.set(cacheKey, records);
      return records;
    }
  }
}
