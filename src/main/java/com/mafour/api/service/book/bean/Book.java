package com.mafour.api.service.book.bean;

import lombok.Data;

@Data
public class Book {

  private Long id;

  private String title;

  private String subTitle;

  private String coverImgUrl;

  private String linkUrl;
}
