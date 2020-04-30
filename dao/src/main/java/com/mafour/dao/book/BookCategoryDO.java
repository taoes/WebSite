package com.mafour.dao.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category")
public class BookCategoryDO {

  @TableId(type = IdType.UUID)
  private Long id;

  private String title;

  private Long bookId;

  private int depth;

  private int ordinal;
}
