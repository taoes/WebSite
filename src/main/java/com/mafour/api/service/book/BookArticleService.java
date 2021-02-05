package com.mafour.api.service.book;

import com.mafour.api.service.book.bean.BookArticle;
import com.mafour.api.service.book.yuque.YuqueDoc;
import java.util.List;

public interface BookArticleService {

  /** 查询文档信息 */
  YuqueDoc findByCategoryId(String bookName, String slug);

  /** 获取指定文章的关键词 */
  String findSearchKey(String slug);

  /** 设置文章为推荐文章 */
  void recommend(boolean set, String slug);

  /** 获取推荐文章 */
  List<BookArticle> recommendList();
}
