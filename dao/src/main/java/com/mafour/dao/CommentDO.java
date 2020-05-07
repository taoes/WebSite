package com.mafour.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName("comment")
public class CommentDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String bookName;

  private String slug;

  private Date createTime;

  private String url;

  private String name;

  private String content;

  private String email;

  private String avatarUrl;
}
