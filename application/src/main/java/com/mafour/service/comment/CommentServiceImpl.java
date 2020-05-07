package com.mafour.service.comment;

import com.mafour.tunnel.CommentTunnel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentTunnel tunnel;

  private final CommentConverter converter;

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
  }
}
