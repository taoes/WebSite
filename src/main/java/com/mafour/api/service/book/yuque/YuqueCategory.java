package com.mafour.api.service.book.yuque;

import java.io.Serializable;
import lombok.Data;

@Data
public class YuqueCategory implements Serializable {

  private String title;

  private String type;

  private int level;

  private String url;

  private long id;

  private String uuid;

  private int depth;

  private String slug;
}

