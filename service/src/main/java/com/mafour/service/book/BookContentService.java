package com.mafour.service.book;

import com.mafour.service.book.bean.BookArticle;
import com.mafour.service.book.yuque.YuqueDoc;

public interface BookContentService {

  /** 查询文档信息 */
  YuqueDoc findByCategoryId(String bookName, String slug);
}
