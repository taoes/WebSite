package com.mafour.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mafour.api.dao.dao.book.BookArticleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface BookContentMapper extends BaseMapper<BookArticleDO> {

  /** 设置文章为星标文章 */
  void updateRecommend(@Param("isSet") boolean set, @Param("slug") String slug);
}
