package com.mafour.service;

import com.mafour.api.Application;
import com.mafour.api.dao.dao.book.BookArticleDO;
import com.mafour.api.dao.tunnel.BookContentTunnel;
import java.util.List;
import java.util.Objects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SiteMapTest {

  @Autowired private ApplicationContext context;

  @Autowired private BookContentTunnel contentTunnel;

  @Test
  public void testApplicationStart() {
    List<BookArticleDO> recommend = contentTunnel.getRecommend();
    assert CollectionUtils.isEmpty(recommend);
    assert Objects.nonNull(context);
  }
}
