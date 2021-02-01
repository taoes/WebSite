package com.mafour.service.book.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookUpdateRecord {

  private String bookName;

  private String bookSlug;

  private String activeType;

  private String slug;

  private String title;
}
