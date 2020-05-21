package com.mafour.api.controller.book.req;

import lombok.Data;

@Data
public class BookToc {

  private String title;

  private String slug;

  private int depth;
}
