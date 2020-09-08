package com.mafour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mafour.dao.book.BookArticleStartDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface BookArticleStartMapper extends BaseMapper<BookArticleStartDO> {
  Double avg(@Param("bookName") String bookName, @Param("slugName") String slug);
}
