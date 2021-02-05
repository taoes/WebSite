package com.mafour.api.dao.dao.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book_article_read")
public class BookArticleReadDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String bookName;

  private String slug;

  private Integer count;
}
