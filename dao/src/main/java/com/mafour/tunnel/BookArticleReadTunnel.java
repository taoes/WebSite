package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.book.BookArticleReadDO;
import com.mafour.mapper.BookArticleReadMapper;
import org.springframework.stereotype.Component;

@Component
public class BookArticleReadTunnel extends ServiceImpl<BookArticleReadMapper, BookArticleReadDO> {


  public Integer createOrIncrement(String bookName,String slug){
    // 查询
    BookArticleReadDO readDO = findBySlugAndBookName(bookName, slug);
    if (readDO == null){
      readDO = new BookArticleReadDO()
          .setCount(1)
          .setBookName(bookName)
          .setSlug(slug);
      this.save(readDO);
      return 1;
    }
    Integer count = readDO.getCount() + 1;
    this.updateById(readDO.setCount(count));
    return count;
  }


  public BookArticleReadDO findBySlugAndBookName(String bookName,String slug){
    LambdaQueryWrapper<BookArticleReadDO> wrapper = new LambdaQueryWrapper<BookArticleReadDO>()
        .eq(BookArticleReadDO::getBookName, bookName)
        .eq(BookArticleReadDO::getSlug, slug)
        .orderByDesc(BookArticleReadDO::getId)
        .last("LIMIT 1");
    return this.getOne(wrapper);
  }
}
