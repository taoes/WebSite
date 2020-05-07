package com.mafour.service.comment;

import java.util.Date;
import lombok.Data;

@Data
public class Comment {

  private Long id;

  private String name;

  private String url;

  private String web;

  private String email;

  private String content;

  private String bookName;

  private String slug;

  private Date createTime;

  private String avatarUrl;
}
