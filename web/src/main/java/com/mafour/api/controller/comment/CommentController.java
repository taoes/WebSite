package com.mafour.api.controller.comment;

import com.mafour.common.Response;
import com.mafour.service.comment.Comment;
import com.mafour.service.comment.CommentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/book/comment")
@RestController
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public List<Comment> all(@RequestParam String bookName, @RequestParam String slug) {
    return commentService.all(bookName, slug);
  }

  @PostMapping
  public Response<String> create(@RequestBody Comment comment) {
    commentService.create(comment);
    log.info("新增评论完成:{}", comment);
    return Response.ok("请求成功");
  }
}
