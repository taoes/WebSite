package com.mafour.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mafour.common.SystemConfigKey;
import lombok.Data;

@Data
@TableName("sys_config")
public class SystemConfigDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private SystemConfigKey configKey;

  private String value;
}
