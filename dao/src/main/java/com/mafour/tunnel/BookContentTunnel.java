package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.book.BookContentDO;
import com.mafour.mapper.BookContentMapper;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookContentTunnel extends ServiceImpl<BookContentMapper, BookContentDO> {

  public Set<Long> findByCategoryId(Long bookId, Set<Long> categoryId) {
    LambdaQueryWrapper<BookContentDO> wrapper =
        new LambdaQueryWrapper<BookContentDO>()
            .select(BookContentDO::getCategoryId)
            .eq(BookContentDO::getBookId, bookId)
            .in(BookContentDO::getCategoryId, categoryId);
    return list(wrapper).stream().map(BookContentDO::getCategoryId).collect(Collectors.toSet());
  }

  public BookContentDO getByCategoryId(Long categoryId) {
    LambdaQueryWrapper<BookContentDO> wrapper =
        new LambdaQueryWrapper<BookContentDO>()
            .eq(BookContentDO::getCategoryId, categoryId)
            .orderByDesc(BookContentDO::getId)
            .last("LIMIT 1");
    return super.getOne(wrapper);
  }
}
