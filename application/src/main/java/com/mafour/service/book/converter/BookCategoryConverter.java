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
        .setBookName(d.getBookName())
        .setBookId(d.getBookId())
        .setTitle(d.getTitle())
        .setType(d.getType())
        .setLevel(d.getLevel())
        .setUrl(d.getUrl())
        .setUuid(d.getUuid())
        .setDepth(d.getDepth())
        .setSlug(d.getSlug())
        .setSequence(d.getSequence());
  }

  @Override
  public BookCategoryDO converterTo(BookCategory category) {
    return new BookCategoryDO()
        .setId(category.getId())
        .setBookName(category.getBookName())
        .setBookId(category.getBookId())
        .setTitle(category.getTitle())
        .setType(category.getType())
        .setLevel(category.getLevel())
        .setUrl(category.getUrl())
        .setUuid(category.getUuid())
        .setDepth(category.getDepth())
        .setSlug(category.getSlug())
        .setSequence(category.getSequence());
  }
}
