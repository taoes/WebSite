package com.mafour.dao.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book_content")
public class BookContentDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private Long categoryId;

  private Long bookId;

  private String content;
}