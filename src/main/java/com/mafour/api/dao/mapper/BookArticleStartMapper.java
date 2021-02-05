package com.mafour.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mafour.api.dao.dao.book.BookArticleStartDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface BookArticleStartMapper extends BaseMapper<BookArticleStartDO> {

  /** 平均值 */
  Double avg(@Param("bookName") String bookName, @Param("slugName") String slug);
}
