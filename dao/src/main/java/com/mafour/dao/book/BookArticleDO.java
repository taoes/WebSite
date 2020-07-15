package com.mafour.dao.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("book_article")
public class BookArticleDO {

  @TableId(type = IdType.AUTO)
  private Integer id;

  private String bookName;

  private String slugName;

  private String slug;

  private String searchKey;

  private Integer wordCount;

  private Long readCount;

  private String cover;

  private String description;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
