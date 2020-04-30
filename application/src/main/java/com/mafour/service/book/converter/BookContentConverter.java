package com.mafour.service.book.converter;

import com.mafour.dao.book.BookContentDO;
import com.mafour.service.AbstractConverter;
import com.mafour.service.book.bean.BookContent;
import org.springframework.stereotype.Component;

@Component
public class BookContentConverter extends AbstractConverter<BookContent, BookContentDO> {

  @Override
  public BookContent converterFrom(BookContentDO d) {
    return new BookContent().setContent(d.getContent());
  }

  @Override
  public BookContentDO converterTo(BookContent bookContent) {
    return null;
  }
}
