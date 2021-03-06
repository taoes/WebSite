package com.mafour.api.service.book;

import com.mafour.api.dao.dao.BookUpdateRecordDO;
import com.mafour.api.dao.tunnel.BookUpdateRecordTunnel;
import com.mafour.api.service.book.bean.BookUpdateRecord;
import com.mafour.api.service.book.converter.BookUpdateRecordConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookRecordServiceImpl implements BookRecordService {

  @Autowired private BookUpdateRecordTunnel tunnel;

  @Autowired private BookUpdateRecordConverter converter;

  @Override
  public void save(BookUpdateRecord record) {
    BookUpdateRecordDO recordDO = converter.converterTo(record);
    tunnel.save(recordDO);
  }
}
