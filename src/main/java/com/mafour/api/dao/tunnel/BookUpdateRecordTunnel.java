package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.BookUpdateRecordDO;
import com.mafour.api.dao.mapper.BookUpdateRecordMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookUpdateRecordTunnel
    extends ServiceImpl<BookUpdateRecordMapper, BookUpdateRecordDO> {

  public List<BookUpdateRecordDO> getLatestChangeList(int count) {
    LambdaQueryWrapper<BookUpdateRecordDO> wrapper =
        new LambdaQueryWrapper<BookUpdateRecordDO>()
            .orderByDesc(BookUpdateRecordDO::getId)
            .last("LIMIT " + count);
    return list(wrapper);
  }
}
