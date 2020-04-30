package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.book.BookCategoryDO;
import com.mafour.mapper.BookCategoryMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookCategoryTunnel extends ServiceImpl<BookCategoryMapper, BookCategoryDO> {

  public List<BookCategoryDO> find(Long bookId) {
    LambdaQueryWrapper<BookCategoryDO> wrapper =
        new LambdaQueryWrapper<BookCategoryDO>()
            .eq(BookCategoryDO::getBookId, bookId)
            .orderByAsc(BookCategoryDO::getOrdinal);
    return list(wrapper);
  }

  public BookCategoryDO findById(Long categoryId) {
    return super.getById(categoryId);
  }
}
