package com.mafour.service.book;

import com.mafour.service.book.bean.Book;
import com.mafour.service.book.converter.BookConverter;
import com.mafour.tunnel.BookTunnel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookTunnel bookTunnel;

  private final BookConverter bookConverter;

  @Override
  public List<Book> findAllBook() {
    return bookTunnel.findAll().stream()
        .map(bookConverter::converterFrom)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Book> find(Long bookId) {
    return Optional.ofNullable(bookTunnel.getById(bookId)).map(bookConverter::converterFrom);
  }

  @Override
  public Optional<Book> findByName(String blog) {
    return Optional.ofNullable(bookTunnel.getByLinkUrl(blog)).map(bookConverter::converterFrom);
  }
}
