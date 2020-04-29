package com.mafour.service.book;

import com.mafour.service.book.bean.Book;
import com.mafour.service.book.converter.BookConverter;
import com.mafour.tunnel.BookTunnel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private BookTunnel bookTunnel;

  private BookConverter bookConverter;

  @Override
  public List<Book> findAllBook() {
    return bookTunnel.findAll().stream()
        .map(bookConverter::converterFrom)
        .collect(Collectors.toList());
  }
}
