package com.mafour.service.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

  private String title;

  private String url;
}
