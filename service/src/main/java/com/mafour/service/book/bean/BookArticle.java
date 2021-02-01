package com.mafour.service.book.bean;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookArticle {

  private Integer id;

  private String bookName;

  private String slug;

  private String book;

  private String slugName;

  private String searchKey;

  private String bodyHtml;

  private Integer wordCount;

  private Long readCount;

  private String cover;

  private String description;

  private boolean recommend;

  private String mkContent;

  private String htmlContent;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
