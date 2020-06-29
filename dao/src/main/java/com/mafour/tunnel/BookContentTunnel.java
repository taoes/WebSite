package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.book.BookArticleDO;
import com.mafour.mapper.BookContentMapper;
import org.springframework.stereotype.Component;

@Component
public class BookContentTunnel extends ServiceImpl<BookContentMapper, BookArticleDO> {

  public void saveOrUpdated(BookArticleDO articleDO) {
    BookArticleDO bookArticleDO = findBySlug(articleDO.getSlug());
    if (bookArticleDO != null) {
      articleDO.setId(bookArticleDO.getId());
      this.updateById(articleDO);
      return;
    }
    save(articleDO);
  }

  public BookArticleDO findBySlug(String slug) {
    Wrapper<BookArticleDO> wrapper =
        new LambdaQueryWrapper<BookArticleDO>()
            .eq(BookArticleDO::getSlug, slug)
            .orderByDesc(BookArticleDO::getId)
            .last("LIMIT 1");
    return this.getOne(wrapper);
  }
}
