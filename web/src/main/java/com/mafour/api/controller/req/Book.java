package com.mafour.api.controller.req;

import lombok.Data;

@Data
public class Book {

  private Integer id; // 文档编号

  private String slug; // 文档路径

  private String name; // 标题
}
