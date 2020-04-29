package com.mafour.service.book.converter;

import com.mafour.dao.BookDO;
import com.mafour.service.AbstractConverter;
import com.mafour.service.book.bean.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends AbstractConverter<Book, BookDO> {

  @Override
  public Book converterFrom(BookDO d) {
    return new Book().setCoverImgUrl(d.getCoverImgUrl()).setTitle(d.getTitle());
  }

  @Override
  public BookDO converterTo(Book book) {
    return null;
  }
}
