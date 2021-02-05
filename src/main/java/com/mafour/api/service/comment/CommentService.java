package com.mafour.api.service.comment;

import java.util.List;

public interface CommentService {

  /** 查询所有评论 */
  List<Comment> allCommentList(String bookName, String slug);

  /** 创建新的评论 */
  void create(Comment comment);
}
