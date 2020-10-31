package com.mafour.api.controller.api;

import com.mafour.service.book.BookArticleService;
import com.mafour.service.book.BookCategoryService;
import com.mafour.service.book.BookService;
import com.mafour.service.book.bean.Book;
import com.mafour.service.book.yuque.YuqueCategory;
import com.mafour.service.book.yuque.YuqueCategoryData;
import com.mafour.service.book.yuque.YuqueDoc;
import com.mafour.service.comment.Comment;
import com.mafour.service.comment.CommentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis/book")
public class BookCategoryController {

  @Autowired private BookCategoryService bookCategoryService;

  @Autowired private BookService bookService;

  @Autowired private BookArticleService articleService;

  @Autowired private CommentService commentService;

  @GetMapping("/{bookId}/category")
  public List<YuqueCategory> getCategoryById(@PathVariable("bookId") Long bookId) {
    Optional<Book> book = bookService.find(bookId);
    YuqueCategoryData byBook = bookCategoryService.findByBook(book.get().getLinkUrl());
    return byBook.getData();
  }

  @GetMapping("/{bookId}")
  public Book getBook(@PathVariable("bookId") Long bookId) {
    Optional<Book> book = bookService.find(bookId);
    return book.get();
  }

  @GetMapping("/{bookLinkUrl}/category/{slug}")
  public YuqueDoc getBook(
      @PathVariable("bookLinkUrl") String bookLinkUrl, @PathVariable("slug") String slug) {
    return articleService.findByCategoryId(bookLinkUrl, slug);
  }

  @GetMapping("/{bookLinkUrl}/category/{slug}/comment")
  public List<Comment> getComment(
      @PathVariable("bookLinkUrl") String bookLinkUrl, @PathVariable("slug") String slug) {
    return commentService.all(bookLinkUrl, slug);
  }
}
