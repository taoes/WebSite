package com.mafour.api.dao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName("message_board")
public class MessageBoardDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private Date createTime;

  private String content;
}
