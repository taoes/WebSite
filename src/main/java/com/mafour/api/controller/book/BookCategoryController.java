package com.mafour.api.controller.book;

import com.mafour.api.service.book.BookArticleService;
import com.mafour.api.service.book.BookCategoryService;
import com.mafour.api.service.book.BookService;
import com.mafour.api.service.book.bean.Book;
import com.mafour.api.service.book.yuque.YuqueCategory;
import com.mafour.api.service.book.yuque.YuqueCategoryData;
import com.mafour.api.service.book.yuque.YuqueDoc;
import com.mafour.api.service.comment.Comment;
import com.mafour.api.service.comment.CommentService;
import com.mafour.api.service.seo.SeoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 图书目录相关 */
@RestController
@RequestMapping("/apis/book")
public class BookCategoryController {

  @Autowired private BookCategoryService bookCategoryService;

  @Autowired private BookService bookService;

  @Autowired private BookArticleService articleService;

  @Autowired private CommentService commentService;

  @Autowired private SeoService seoService;

  /** 获取书籍目录信息 */
  @GetMapping("/{bookId}/category")
  public List<YuqueCategory> getCategoryById(@PathVariable("bookId") Long bookId) {
    Optional<Book> book = bookService.find(bookId);
    String linkUrl = book.map(Book::getLinkUrl).orElse(null);
    YuqueCategoryData byBook = bookCategoryService.findByBook(linkUrl);
    return byBook.getData();
  }

  /** 获取书籍信息 */
  @GetMapping("/{bookId}")
  public Book getBook(@PathVariable("bookId") Long bookId) {
    Optional<Book> book = bookService.find(bookId);
    seoService.push(String.valueOf(bookId));
    return book.orElse(null);
  }

  /** 查询文章信息 */
  @GetMapping("/{book}/category/{slug}")
  public YuqueDoc getBook(@PathVariable String book, @PathVariable String slug) {
    YuqueDoc doc = articleService.findByCategoryId(book, slug);
    seoService.push(book, slug);
    return doc;
  }

  /** 获取文章的评论信息 */
  @GetMapping("/{book}/category/{slug}/comment")
  public List<Comment> getComment(@PathVariable String book, @PathVariable String slug) {
    return commentService.allCommentList(book, slug);
  }
}
