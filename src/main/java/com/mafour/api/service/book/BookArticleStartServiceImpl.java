package com.mafour.api.service.book;

import com.mafour.api.dao.dao.book.BookArticleDO;
import com.mafour.api.service.ding.DingTalkService;
import com.mafour.api.dao.tunnel.BookArticleStartTunnel;
import com.mafour.api.dao.tunnel.BookContentTunnel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookArticleStartServiceImpl implements BookArticleStartService {

  private final DingTalkService talkService;

  private final BookArticleStartTunnel tunnel;

  private final BookContentTunnel contentTunnel;

  @Override
  public Integer getStart(String bookName, String slug) {
    Integer count = tunnel.count(bookName, slug);
    if (count == null) {
      count = 3;
    }
    return count;
  }

  @Override
  public Integer createStart(String bookName, String slug, Integer value) {
    tunnel.add(bookName, slug, value);
    BookArticleDO articleDO = contentTunnel.findBySlug(slug);
    talkService.sendStatMsf(articleDO.getBookName(), articleDO.getSlugName(), value);
    return getStart(bookName, slug);
  }
}
