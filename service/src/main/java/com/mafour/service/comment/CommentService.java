package com.mafour.service.comment;

import java.util.List;

public interface CommentService {

  List<Comment> all(String bookName, String slug);

  void create(Comment comment);
}
