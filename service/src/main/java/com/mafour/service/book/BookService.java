package com.mafour.service.book;

import com.mafour.service.book.bean.Book;
import com.mafour.service.book.bean.BookCategory;
import java.util.List;

public interface BookService {

  /** 查询所有图书列表 */
  List<Book> findAllBook();

}
