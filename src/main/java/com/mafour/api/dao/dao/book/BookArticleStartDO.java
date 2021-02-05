package com.mafour.api.dao.dao.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book_article_start")
public class BookArticleStartDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String bookName;

  private String slugName;

  private Integer value;
}
