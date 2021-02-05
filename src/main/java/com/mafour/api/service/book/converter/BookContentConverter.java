package com.mafour.api.service.book.converter;

import com.mafour.api.dao.dao.book.BookArticleDO;
import com.mafour.api.service.AbstractConverter;
import com.mafour.api.service.book.bean.BookArticle;
import org.springframework.stereotype.Component;

@Component
public class BookContentConverter extends AbstractConverter<BookArticle, BookArticleDO> {

  @Override
  public BookArticle converterFrom(BookArticleDO doc) {
    return new BookArticle()
        .setId(doc.getId())
        .setSearchKey(doc.getSearchKey())
        .setCover(doc.getCover())
        .setSlug(doc.getSlug())
        .setBook(doc.getBook())
        .setRecommend(doc.isRecommend())
        .setSlugName(doc.getSlugName())
        .setBookName(doc.getBookName())
        .setUpdatedAt(doc.getUpdatedAt())
        .setCreatedAt(doc.getCreatedAt())
        .setWordCount(doc.getWordCount())
        .setDescription(doc.getDescription());
  }

  @Override
  public BookArticleDO converterTo(BookArticle content) {
    return new BookArticleDO()
        .setId(content.getId())
        .setSearchKey(content.getSearchKey())
        .setCover(content.getCover())
        .setSlug(content.getSlug())
        .setBook(content.getBook())
        .setRecommend(content.isRecommend())
        .setBookName(content.getBookName())
        .setSlugName(content.getSlugName())
        .setUpdatedAt(content.getUpdatedAt())
        .setCreatedAt(content.getCreatedAt())
        .setWordCount(content.getWordCount())
        .setDescription(content.getDescription())
        .setMkContent(content.getMkContent())
        .setHtmlContent(content.getHtmlContent());
  }
}
