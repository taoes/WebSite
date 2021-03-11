package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.book.BookCategoryDO;
import com.mafour.api.dao.mapper.BookCategoryMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookCategoryTunnel extends ServiceImpl<BookCategoryMapper, BookCategoryDO> {

  public void cleanAndSaveBatch(String bookName, List<BookCategoryDO> data) {
    clean(bookName);
    data.forEach(BookCategoryDO::cleanId);
    super.saveBatch(data);
  }

  private void clean(String bookName) {
    LambdaUpdateWrapper<BookCategoryDO> wrapper =
        new LambdaUpdateWrapper<BookCategoryDO>().eq(BookCategoryDO::getBookName, bookName);
    this.remove(wrapper);
  }
}
