package com.mafour.service.book;

import com.mafour.service.book.bean.BookUpdateRecord;

public interface BookRecordService {

  /** 保存更新记录 */
  void save(BookUpdateRecord record);
}
