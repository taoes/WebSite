package com.mafour.api.service.book.bean;

import lombok.Data;

@Data
public class BookCategory {

  private Long id;

  private String bookName;

  private int bookId;

  private String title;

  private String type;

  private int level;

  private String url;

  private String uuid;

  private int depth;

  private String slug;

  private int sequence;
}
