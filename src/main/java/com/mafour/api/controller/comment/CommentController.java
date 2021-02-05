package com.mafour.api.controller.comment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mafour.api.common.Response;
import com.mafour.api.service.comment.Comment;
import com.mafour.api.service.comment.CommentService;
import com.mafour.api.service.redis.RedisService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book/comment")
public class CommentController {

  private final CommentService commentService;

  private final RedisService redisService;

  @GetMapping
  public List<Comment> allCommentList(@RequestParam String bookName, @RequestParam String slug) {
    String cacheKey = String.format("WEB:CATEGORY:%s:CONTENT:%s:COMMENT", bookName, slug);
    List<Comment> commentList = redisService.find(cacheKey, new TypeReference<List<Comment>>() {});
    if (!CollectionUtils.isEmpty(commentList)) {
      return commentList;
    }
    commentList = commentService.allCommentList(bookName, slug);
    redisService.set(cacheKey, commentList);
    return commentList;
  }

  /** 新增评论接口 */
  @PostMapping
  public Response<String> create(@RequestBody Comment comment) {
    String bookName = comment.getBookName();
    String slug = comment.getSlug();
    commentService.create(comment);
    log.info("新增评论完成:{}", comment);

    String cacheKey = String.format("WEB:CATEGORY:%s:CONTENT:%s:COMMENT", bookName, slug);
    redisService.del(cacheKey);
    log.info("清除评论缓存:{}", cacheKey);
    return Response.ok("请求成功");
  }
}
