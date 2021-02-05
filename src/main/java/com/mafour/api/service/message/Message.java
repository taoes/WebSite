package com.mafour.api.service.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  private static final DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private Long id;

  private String content;

  private Date createTime;

  public String getFormatDate() {
    if (this.createTime == null) {
      return "";
    }
    return f.format(this.createTime);
  }
}
