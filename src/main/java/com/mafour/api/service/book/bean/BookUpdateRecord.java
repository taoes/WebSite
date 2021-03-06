package com.mafour.api.service.book.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookUpdateRecord {

  private String bookName;

  private String bookSlug;

  private String activeType;

  private String slug;

  private String title;

  private String desc;

  public BookUpdateRecord(
      String bookName, String bookSlug, String activeType, String slug, String title) {
    this.bookName = bookName;
    this.bookSlug = bookSlug;
    this.activeType = activeType;
    this.slug = slug;
    this.title = title;
  }
}
