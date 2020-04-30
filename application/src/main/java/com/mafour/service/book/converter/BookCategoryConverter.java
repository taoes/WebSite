package com.mafour.service.book.converter;

import com.mafour.dao.book.BookCategoryDO;
import com.mafour.service.AbstractConverter;
import com.mafour.service.book.bean.BookCategory;
import org.springframework.stereotype.Component;

@Component
public class BookCategoryConverter extends AbstractConverter<BookCategory, BookCategoryDO> {

  @Override
  public BookCategory converterFrom(BookCategoryDO d) {
    return new BookCategory()
        .setId(d.getId())
        .setTitle(d.getTitle())
        .setDepth(d.getDepth())
        .setOrdinal(d.getOrdinal());
  }

  @Override
  public BookCategoryDO converterTo(BookCategory book) {
    return null;
  }
}
