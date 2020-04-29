package com.mafour.service.book.bean;

import java.time.LocalDate;
import lombok.Data;

@Data
public class BookArticle {

  private String title;

  private String content;

  private LocalDate createTime;

  private LocalDate updateTime;
}
