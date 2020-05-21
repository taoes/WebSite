package com.mafour.service.book;

import com.mafour.service.book.bean.BookContent;
import com.mafour.service.book.yuque.YuqueDoc;

public interface BookContentService {

  BookContent findByCategoryId(Long categoryId);

  /** 查询文档信息 */
  YuqueDoc findByCategoryId(String bookName, String slug);
}
