package com.mafour.api.dao.dao.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category")
public class BookCategoryDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String title;

  private String bookName;

  private int bookId;

  private String type;

  private int level;

  private String url;

  private String uuid;

  private int depth;

  private String slug;

  private int sequence;

  public void cleanId() {
    this.id = null;
  }
}
