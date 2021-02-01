package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.MessageBoardDO;
import com.mafour.mapper.MessageBoardMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MessageBoardTunnel extends ServiceImpl<MessageBoardMapper, MessageBoardDO> {

  public List<MessageBoardDO> findList() {
    Wrapper<MessageBoardDO> wrapper =
        new LambdaQueryWrapper<MessageBoardDO>().orderByDesc(MessageBoardDO::getCreateTime);
    return list(wrapper);
  }
}
