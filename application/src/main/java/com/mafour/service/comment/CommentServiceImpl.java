package com.mafour.service.comment;

import com.mafour.service.ding.DingTalkService;
import com.mafour.tunnel.CommentTunnel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private final CommentTunnel tunnel;

  @Autowired private final CommentConverter converter;

  @Autowired private DingTalkService dingTalkService;

  public CommentServiceImpl(CommentTunnel tunnel, CommentConverter converter) {
    this.tunnel = tunnel;
    this.converter = converter;
  }

  @Override
  public List<Comment> all(String bookName, String slug) {
    return tunnel.findList(bookName, slug).stream()
        .map(converter::converterFrom)
        .collect(Collectors.toList());
  }

  @Override
  public void create(Comment comment) {
    Optional.ofNullable(comment).map(converter::converterTo).ifPresent(tunnel::save);
    dingTalkService.sendCommentMsf(comment.getName(),comment.getContent(),comment.getBookName(),comment.getSlug());
  }
}
