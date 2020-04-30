package com.mafour.service.book.bean;

import lombok.Data;

@Data
public class BookCategory {

  private Long id;

  private String title;

  private int depth;

  private int ordinal;

  private boolean existContent;
}
