package com.mafour.api.service.book;

import com.mafour.api.service.book.yuque.YuqueCategoryData;

public interface BookCategoryService {

  /** 获取文章目录数据*/
  YuqueCategoryData findByBook(String yuqueName);
}
