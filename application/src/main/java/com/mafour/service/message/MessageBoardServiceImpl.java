package com.mafour.service.message;

import com.mafour.dao.MessageBoardDO;
import com.mafour.tunnel.MessageBoardTunnel;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageBoardServiceImpl implements MessageBoardService {

  @Autowired private MessageBoardTunnel tunnel;

  @Override
  public List<Message> all() {
    List<MessageBoardDO> messageBoardDOS = tunnel.findList();
    return messageBoardDOS.stream()
        .map(
            messageBoardDO ->
                new Message(
                    messageBoardDO.getId(),
                    messageBoardDO.getContent(),
                    messageBoardDO.getCreateTime()))
        .collect(Collectors.toList());
  }

  @Override
  public void create(String content) {
    MessageBoardDO messageBoardDO =
        new MessageBoardDO().setContent(content).setCreateTime(new Date());
    tunnel.save(messageBoardDO);
  }
}
