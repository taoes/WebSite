package com.mafour.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book")
public class BookDO {

  @TableId(type = IdType.UUID)
  private Long id;

  private String title;

  private String coverImgUrl;

  private String linkUrl;
}
