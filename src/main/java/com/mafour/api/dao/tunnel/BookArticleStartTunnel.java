package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.book.BookArticleStartDO;
import com.mafour.api.dao.mapper.BookArticleStartMapper;
import org.springframework.stereotype.Component;

@Component
public class BookArticleStartTunnel
    extends ServiceImpl<BookArticleStartMapper, BookArticleStartDO> {

  public Integer count(String bookName, String slug) {
    Double start = this.baseMapper.avg(bookName, slug);
    return start == null ? null : start.intValue();
  }

  public void add(String bookName, String slug, Integer value) {
    BookArticleStartDO startDO =
        new BookArticleStartDO().setBookName(bookName).setSlugName(slug).setValue(value);
    save(startDO);
  }
}
