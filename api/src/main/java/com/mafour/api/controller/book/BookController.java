package com.mafour.api.controller.book;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/book")
public class BookController {

  @GetMapping("/{bookName}/toc")
  public List<BookToc> getTocByName(@PathVariable("bookName") String bookName) {
    return null;
  }
}
