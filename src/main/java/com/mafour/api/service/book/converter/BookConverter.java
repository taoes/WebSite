package com.mafour.api.service.book.converter;

import com.mafour.api.dao.dao.book.BookDO;
import com.mafour.api.service.AbstractConverter;
import com.mafour.api.service.book.bean.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends AbstractConverter<Book, BookDO> {

  @Override
  public Book converterFrom(BookDO d) {
    return new Book()
        .setId(d.getId())
        .setCoverImgUrl(d.getCoverImgUrl())
        .setTitle(d.getTitle())
        .setSubTitle(d.getSubTitle())
        .setLinkUrl(d.getLinkUrl());
  }

  @Override
  public BookDO converterTo(Book book) {
    return null;
  }
}
