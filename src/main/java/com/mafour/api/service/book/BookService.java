package com.mafour.api.service.book;

import com.mafour.api.service.book.bean.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

  /** 查询所有图书列表 */
  List<Book> findAllBook();

  Optional<Book> find(Long bookId);

  Optional<Book> findByName(String blog);
}
