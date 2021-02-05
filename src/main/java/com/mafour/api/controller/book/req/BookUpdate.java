package com.mafour.api.controller.book.req;

import lombok.Data;

@Data
public class BookUpdate {

  private Integer id; // 文档编号

  private String slug; // 文档路径

  private String title; // 标题

  private Integer book_id; // 仓库编号，就是 repo_id

  private Book book; // 仓库信息 <BookSerializer>，就是 repo 信息

  private String content_updated_at; // 文档内容更新时间

  private String action_type;
}
