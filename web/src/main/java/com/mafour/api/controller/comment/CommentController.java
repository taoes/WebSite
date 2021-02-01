package com.mafour.api.controller.comment;

import com.mafour.common.Response;
import com.mafour.service.comment.Comment;
import com.mafour.service.comment.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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

  private final RedissonClient redissonClient;

  @GetMapping
  public List<Comment> allCommentList(@RequestParam String bookName, @RequestParam String slug) {
    String cacheKey = String.format("WEB:CATEGORY:%s:CONTENT:%s:COMMENT", bookName, slug);
    RBucket<List<Comment>> commentBucket = redissonClient.getBucket(cacheKey);
    if (commentBucket.isExists()) {
      return commentBucket.get();
    }
    List<Comment> commentList = commentService.allCommentList(bookName, slug);
    commentBucket.set(commentList);
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
    redissonClient.getBucket(cacheKey).delete();
    log.info("清除评论缓存:{}", cacheKey);
    return Response.ok("请求成功");
  }
}
