package com.mafour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mafour.dao.book.BookCategoryDO;
import com.mafour.dao.book.BookContentDO;
import org.springframework.stereotype.Component;

@Component
public interface BookContentMapper extends BaseMapper<BookContentDO> {}
