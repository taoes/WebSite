package com.mafour.service.book;

public interface BookArticleStartService {

  /** 获取文章的评级 */
  Integer getStart(String bookName, String slug);

  /* 文章评级 */
  Integer createStart(String bookName, String slug, Integer value);
}
