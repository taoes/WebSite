package com.mafour.service.link;

import lombok.Data;

@Data
public class Link {

  private String title;

  private String content;

  private String url;


  public static Link newLink(){
    return new Link().setContent("Font Awesome 是一套绝佳的图标字体库和CSS框架。").setTitle("【学习 Font Awesome】").setUrl("https://www.zhoutao123.com");
  }
}
