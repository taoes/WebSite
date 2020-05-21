package com.mafour.api.controller.book;

import com.mafour.api.controller.book.req.Book;
import com.mafour.api.controller.book.req.BookUpdate;
import com.mafour.dao.BookUpdateRecordDO;
import com.mafour.service.SeoService;
import com.mafour.tunnel.BookUpdateRecordTunnel;
import java.io.IOException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

  private final BookUpdateRecordTunnel tunnel;

  private final RedissonClient redissonClient;

  private final SeoService seoService;

  @PostMapping("/update")
  public String updateRecord(@RequestBody BookRecord record) throws IOException {
    log.info("save book record is start ....");
    BookUpdate data = record.getData();
    String slug = data.getSlug();

    Book book = data.getBook();
    String name = book.getName();
    String bookSlug = book.getSlug();

    BookUpdateRecordDO recordDO =
        new BookUpdateRecordDO()
            .setSlug(slug)
            .setSlugName(data.getTitle())
            .setActiveType(data.getAction_type())
            .setBookName(name)
            .setBookSlug(bookSlug)
            .setUpdatedAt(new Date());
    tunnel.save(recordDO);
    log.info("save book record is ok....");

    // 清除缓存
    String categoryCache = String.format("CATEGORY:%s", bookSlug);
    redissonClient.getBucket(categoryCache).delete();

    String slugCache = String.format("CATEGORY:%s:CONTENT:%s", bookSlug, slug);
    redissonClient.getBucket(slugCache).delete();
    log.info("cache :{} & {} clean is ok ...", categoryCache, slugCache);

    // 推送到SEO
    seoService.push(bookSlug, slug);
    return "OK";
  }

  @Data
  static class BookRecord {
    BookUpdate data;
  }
}
