package com.mafour.api.service.book.converter;

import com.mafour.api.dao.dao.BookUpdateRecordDO;
import com.mafour.api.service.AbstractConverter;
import com.mafour.api.service.book.bean.BookUpdateRecord;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class BookUpdateRecordConverter
    extends AbstractConverter<BookUpdateRecord, BookUpdateRecordDO> {

  @Override
  public BookUpdateRecord converterFrom(BookUpdateRecordDO d) {
    return new BookUpdateRecord()
        .setBookName(d.getBookName())
        .setSlug(d.getSlug())
        .setBookSlug(d.getBookSlug())
        .setTitle(d.getSlugName());
  }

  @Override
  public BookUpdateRecordDO converterTo(BookUpdateRecord record) {
    return new BookUpdateRecordDO()
        .setSlug(record.getSlug())
        .setSlugName(record.getTitle())
        .setActiveType(record.getActiveType())
        .setBookName(record.getBookName())
        .setBookSlug(record.getBookSlug())
        .setUpdatedAt(new Date());
  }
}
