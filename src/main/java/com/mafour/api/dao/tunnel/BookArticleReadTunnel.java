package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.book.BookArticleReadDO;
import com.mafour.api.dao.mapper.BookArticleReadMapper;
import org.springframework.stereotype.Component;

@Component
public class BookArticleReadTunnel extends ServiceImpl<BookArticleReadMapper, BookArticleReadDO> {

  public Integer createOrIncrement(String book, String slug) {
    BookArticleReadDO readDO = findBySlugAndBookName(book, slug);
    if (readDO == null) {
      readDO = new BookArticleReadDO().setCount(1).setBookName(book).setSlug(slug);
      this.save(readDO);
      return 1;
    }
    Integer count = readDO.getCount() + 1;
    this.updateById(readDO.setCount(count));
    return count;
  }

  public BookArticleReadDO findBySlugAndBookName(String book, String slug) {
    LambdaQueryWrapper<BookArticleReadDO> wrapper =
        new LambdaQueryWrapper<BookArticleReadDO>()
            .eq(BookArticleReadDO::getBookName, book)
            .eq(BookArticleReadDO::getSlug, slug)
            .orderByDesc(BookArticleReadDO::getId)
            .last("LIMIT 1");
    return this.getOne(wrapper);
  }
}
