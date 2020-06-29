package com.mafour.service.book;

import com.mafour.service.book.bean.BookCategory;
import com.mafour.service.book.yuque.YuqueCategoryData;
import java.util.Optional;

public interface BookCategoryService {

  YuqueCategoryData findByBook(String yuqueName);
}
