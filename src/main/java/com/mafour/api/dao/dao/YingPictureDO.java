package com.mafour.api.dao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ying_pic")
public class YingPictureDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String url;

  private String kind;

  private String remark;

  private Integer ordinal;
}
