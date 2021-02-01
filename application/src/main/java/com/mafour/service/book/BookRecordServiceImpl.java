package com.mafour.service.book;

import com.mafour.dao.BookUpdateRecordDO;
import com.mafour.service.book.bean.BookUpdateRecord;
import com.mafour.tunnel.BookUpdateRecordTunnel;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookRecordServiceImpl implements BookRecordService {

  @Autowired private BookUpdateRecordTunnel tunnel;

  @Override
  public void save(BookUpdateRecord record) {
    BookUpdateRecordDO recordDO =
        new BookUpdateRecordDO()
            .setSlug(record.getSlug())
            .setSlugName(record.getTitle())
            .setActiveType(record.getActiveType())
            .setBookName(record.getBookName())
            .setBookSlug(record.getBookSlug())
            .setUpdatedAt(new Date());
    tunnel.save(recordDO);
  }
}
