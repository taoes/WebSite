package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.book.BookArticleDO;
import com.mafour.api.dao.mapper.BookContentMapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookContentTunnel extends ServiceImpl<BookContentMapper, BookArticleDO> {

  public void saveOrUpdated(BookArticleDO articleDO) {
    BookArticleDO bookArticleDO = findBySlug(articleDO.getSlug());
    if (bookArticleDO == null) {
      log.info("文章:{} 不存在,即将保存文章内容", articleDO.getSlug());
      save(articleDO);
      return;
    }
    log.info("文章:{} 存在,即将更新文章内容", bookArticleDO.getSlug());
    articleDO.setId(bookArticleDO.getId());
    this.updateById(articleDO);
  }

  /** 查询文章 */
  public BookArticleDO findBySlug(String slug) {
    Wrapper<BookArticleDO> wrapper =
        new LambdaQueryWrapper<BookArticleDO>()
            .eq(BookArticleDO::getSlug, slug)
            .orderByDesc(BookArticleDO::getId)
            .last("LIMIT 1");
    return this.getOne(wrapper);
  }

  /** 搜索文章 */
  public String getSearchBySlug(String slug) {
    Wrapper<BookArticleDO> wrapper =
        new LambdaQueryWrapper<BookArticleDO>()
            .select(BookArticleDO::getSearchKey)
            .eq(BookArticleDO::getSlug, slug)
            .orderByDesc(BookArticleDO::getId)
            .last("LIMIT 1");
    return Optional.ofNullable(this.getOne(wrapper)).map(BookArticleDO::getSearchKey).orElse("");
  }

  public void updateRecommend(Boolean set, String slug) {
    getBaseMapper().updateRecommend(set, slug);
  }

  public List<BookArticleDO> getRecommend() {
    Wrapper<BookArticleDO> wrapper =
        new LambdaQueryWrapper<BookArticleDO>()
            .eq(BookArticleDO::isRecommend, true)
            .orderByDesc(BookArticleDO::getId);
    return this.list(wrapper);
  }

  public Map<String, String> findDescBySlug(Set<String> slugList) {
    LambdaQueryWrapper<BookArticleDO> wrapper =
        new LambdaQueryWrapper<BookArticleDO>()
            .select(BookArticleDO::getSlug, BookArticleDO::getDescription)
            .in(BookArticleDO::getSlug, slugList);
    return list(wrapper).stream()
        .collect(Collectors.toMap(BookArticleDO::getSlug, BookArticleDO::getDescription));
  }

  /** 返回所有的文章数据 */
  public Map<String, String> getData() {
    return null;
  }
}
