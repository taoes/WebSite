package com.mafour.api.controller.book;

import com.mafour.api.controller.book.req.Book;
import com.mafour.api.controller.book.req.BookUpdate;
import com.mafour.api.service.book.BookRecordService;
import com.mafour.api.service.book.bean.BookUpdateRecord;
import com.mafour.api.service.redis.RedisService;
import com.mafour.api.service.seo.SeoService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

  private final BookRecordService bookRecordService;

  private final RedisService redisService;

  private final SeoService seoService;

  @PostMapping("/update")
  public String updateRecord(@RequestBody BookRecord recordCallbackData) throws IOException {
    BookUpdate data = recordCallbackData.getData();
    String slug = data.getSlug();
    Book book = data.getBook();
    String bookSlug = book.getSlug();

    log.info(
        "接收到更新回调通知，bName={} bSlug={} aName={} aSlug={}",
        book.getName(),
        book.getSlug(),
        data.getTitle(),
        book.getSlug());

    // 保存记录
    BookUpdateRecord record =
        new BookUpdateRecord(
            book.getName(), book.getSlug(), data.getAction_type(), data.getSlug(), data.getTitle());
    bookRecordService.save(record);

    // 清除缓存 & SEO
    cleanCache(bookSlug, slug);
    seoService.push(bookSlug, slug);
    return "OK";
  }

  /** 清除缓存数据 */
  private void cleanCache(String book, String slug) {
    String categoryCache = String.format("WEB:CATEGORY:%s", book);
    redisService.del(categoryCache);

    String slugCache = String.format("WEB:CATEGORY:%s:CONTENT:%s", book, slug);
    redisService.del(slugCache);
    log.info("缓存 :{} & {} 清除完成", categoryCache, slugCache);
  }

  @Data
  static class BookRecord {
    BookUpdate data;
  }
}
