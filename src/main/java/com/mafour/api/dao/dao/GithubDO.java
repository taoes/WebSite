package com.mafour.api.dao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("github")
public class GithubDO {

  @TableId(type = IdType.UUID)
  private Long id;

  private String title;

  private String coverImgUrl;

  private String linkUrl;
}
