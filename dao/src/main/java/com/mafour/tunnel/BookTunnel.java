package com.mafour.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.BookDO;
import com.mafour.mapper.BookMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookTunnel extends ServiceImpl<BookMapper, BookDO> {

  public List<BookDO> findAll() {
    return list();
  }
}
