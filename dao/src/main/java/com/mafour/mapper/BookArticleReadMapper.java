package com.mafour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mafour.dao.book.BookArticleDO;
import com.mafour.dao.book.BookArticleReadDO;
import org.springframework.stereotype.Component;

@Component
public interface BookArticleReadMapper extends BaseMapper<BookArticleReadDO> {}
