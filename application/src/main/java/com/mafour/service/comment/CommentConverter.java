package com.mafour.service.comment;

import com.mafour.dao.CommentDO;
import com.mafour.service.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter extends AbstractConverter<Comment, CommentDO> {

  @Override
  public Comment converterFrom(CommentDO d) {
    return new Comment()
        .setId(d.getId())
        .setName(d.getName())
        .setEmail(d.getEmail())
        .setUrl(d.getUrl())
        .setContent(d.getContent())
        .setCreateTime(d.getCreateTime())
        .setAvatarUrl(d.getAvatarUrl());
  }

  @Override
  public CommentDO converterTo(Comment comment) {
    return new CommentDO()
        .setId(comment.getId())
        .setEmail(comment.getEmail())
        .setName(comment.getName())
        .setUrl(comment.getUrl())
        .setBookName(comment.getBookName())
        .setSlug(comment.getSlug())
        .setAvatarUrl(comment.getAvatarUrl())
        .setContent(comment.getContent());
  }
}
