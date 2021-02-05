package com.mafour.api.controller.message;

import com.mafour.api.common.Response;
import com.mafour.api.service.message.Message;
import com.mafour.api.service.message.MessageBoardService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/message/board")
@RestController
public class MessageBoardController {

  @Autowired private MessageBoardService messageBoardService;

  @GetMapping
  public Response<List<Message>> all() {
    List<Message> messageList = messageBoardService.all();
    return Response.ok(messageList);
  }

  @PostMapping
  public Response<String> save(@RequestBody Message message) {
    messageBoardService.create(message.getContent());
    return Response.ok("请求成功");
  }
}
