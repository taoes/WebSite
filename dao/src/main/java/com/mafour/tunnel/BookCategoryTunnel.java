package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.book.BookCategoryDO;
import com.mafour.mapper.BookCategoryMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookCategoryTunnel extends ServiceImpl<BookCategoryMapper, BookCategoryDO> {

  public void cleanAndSaveBatch(String bookName, List<BookCategoryDO> data) {
    clean(bookName);
    super.saveBatch(data);
  }

  private void clean(String bookName) {
    LambdaUpdateWrapper<BookCategoryDO> wrapper =
        new LambdaUpdateWrapper<BookCategoryDO>().eq(BookCategoryDO::getBookName, bookName);
    this.remove(wrapper);
  }
}
