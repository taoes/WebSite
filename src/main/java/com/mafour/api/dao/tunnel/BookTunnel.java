package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.book.BookDO;
import com.mafour.api.dao.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BookTunnel extends ServiceImpl<BookMapper, BookDO> {

  public List<BookDO> findAll() {
    return list();
  }

  public List<String> allId(Function<Long, String> converter) {
    Wrapper<BookDO> wrapper = new LambdaQueryWrapper<BookDO>().select(BookDO::getId);
    return this.list(wrapper).stream()
        .map(BookDO::getId)
        .map(converter)
        .collect(Collectors.toList());
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
