package com.mafour.service.book;

import com.mafour.service.book.bean.BookCategory;
import com.mafour.service.book.yuque.YuqueCategoryData;
import java.util.Optional;

public interface BookCategoryService {

  YuqueCategoryData findByBook(String yuqueName);

  /**
   * 查询目录信息
   *
   * @return
   */
  Optional<BookCategory> findById(Long categoryId);
}
