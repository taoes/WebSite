package com.mafour.service.book.bean;

import lombok.Data;

@Data
public class BookToc {

  private String title;

  private String slug;

  private int depth;
}
