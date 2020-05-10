package com.mafour.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName("update_record")
public class BookUpdateRecordDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String bookName;

  private String bookSlug;

  private String activeType;

  private String slug;

  private String slugName;

  private Date updatedAt;
}
