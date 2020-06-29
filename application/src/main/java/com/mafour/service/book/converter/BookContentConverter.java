package com.mafour.service.book.converter;

import com.mafour.dao.book.BookArticleDO;
import com.mafour.service.AbstractConverter;
import com.mafour.service.book.bean.BookArticle;
import org.springframework.stereotype.Component;

@Component
public class BookContentConverter extends AbstractConverter<BookArticle, BookArticleDO> {

  @Override
  public BookArticle converterFrom(BookArticleDO doc) {
    return new BookArticle()
        .setId(doc.getId())
        .setBody(doc.getBody())
        .setCover(doc.getCover())
        .setSlug(doc.getSlug())
        .setUpdatedAt(doc.getUpdatedAt())
        .setBodyHtml(doc.getBodyHtml())
        .setCreatedAt(doc.getCreatedAt())
        .setWordCount(doc.getWordCount())
        .setDescription(doc.getDescription());
  }

  @Override
  public BookArticleDO converterTo(BookArticle content) {
    return new BookArticleDO()
        .setId(content.getId())
        .setBody(content.getBody())
        .setCover(content.getCover())
        .setSlug(content.getSlug())
        .setUpdatedAt(content.getUpdatedAt())
        .setBodyHtml(content.getBodyHtml())
        .setCreatedAt(content.getCreatedAt())
        .setWordCount(content.getWordCount())
        .setDescription(content.getDescription());
  }
}
