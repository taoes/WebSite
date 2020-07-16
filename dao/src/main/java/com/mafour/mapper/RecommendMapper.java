package com.mafour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mafour.dao.book.BookArticleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface BookContentMapper extends BaseMapper<BookArticleDO> {

  /** 设置文章为星标文章 */
  void updateRecommend(@Param("isSet") boolean set,@Param("slug") String slug);



}
