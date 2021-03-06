package com.mafour.api.controller.book;

import com.mafour.api.controller.book.req.Book;
import com.mafour.api.controller.book.req.BookUpdate;
import com.mafour.api.service.book.BookRecordService;
import com.mafour.api.service.book.bean.BookUpdateRecord;
import com.mafour.api.service.redis.RedisService;
import com.mafour.api.service.seo.SeoService;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired private BookRecordService bookRecordService;

  @Autowired private RedisService redisService;

  @Autowired private SeoService seoService;

  @Getter private static final RuntimeException notData = null;

  @PostMapping("/update")
  public String updateRecord(@RequestBody BookRecord recordCallbackData) {
    BookUpdate data = recordCallbackData.getData();
    String slug = data.getSlug();
    Book book = data.getBook();
    String bookSlug = book.getSlug();

    // 保存文章变动记录
    BookUpdateRecord record =
        new BookUpdateRecord(
            book.getName(), book.getSlug(), data.getAction_type(), data.getSlug(), data.getTitle());
    bookRecordService.save(record);

    cleanCache(bookSlug, slug);
    seoService.push(bookSlug, slug);
    return "OK";
  }

  /** 清除缓存数据 */
  private void cleanCache(String book, String slug) {
    // 清除目录缓存
    String categoryCache = String.format("WEB:CATEGORY:%s", book);
    redisService.del(categoryCache);

    // 清除文章缓存
    String slugCache = String.format("WEB:CATEGORY:%s:CONTENT:%s", book, slug);
    redisService.del(slugCache);

    // 清除最新发布的文章缓存
    redisService.del("WEB:PUBLISH:LATEST:10");
    log.info("缓存 :{} & {} 清除完成", categoryCache, slugCache);
  }

  @Data
  static class BookRecord {
    BookUpdate data;
  }
}
