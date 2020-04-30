package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.book.BookDO;
import com.mafour.mapper.BookMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookTunnel extends ServiceImpl<BookMapper, BookDO> {

  public List<BookDO> findAll() {
    return list();
  }

  public BookDO getByLinkUrl(String url) {
    LambdaQueryWrapper<BookDO> wrapper =
        new LambdaQueryWrapper<BookDO>()
            .eq(BookDO::getLinkUrl, url)
            .orderByAsc(BookDO::getId)
            .last("LIMIT 1");

    return this.getOne(wrapper);
  }
}
