package com.mafour.service.book.yuque;

import lombok.Data;

@Data
public class YuqueCategory {

  private String type;
  private String title;
  private int level;
  private String url;
  private long id;
  private String uuid;
  private int depth;
  private String slug;
}
