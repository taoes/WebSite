package com.mafour.api.dao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("recommend")
public class RecommendDO {

  @TableId(type = IdType.UUID)
  private Long id;

  private String name;
  private String remark;
  private String type;
  private String link;
  private String cover;
}
