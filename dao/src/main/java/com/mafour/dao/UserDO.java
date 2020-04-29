package com.mafour.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mafour.common.UserStatus;
import lombok.Data;

@Data
@TableName("cms_user")
public class UserDO {

  @TableId(type = IdType.UUID)
  private Long id;

  private String username;

  private String salt;

  private String password;

  private UserStatus status;
}
