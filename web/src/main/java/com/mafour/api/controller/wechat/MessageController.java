package com.mafour.api.controller.wechat;

import com.mafour.service.ding.DingTalkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ying/msg")
@RestController
public class MessageController {

  private final DingTalkService dingTalkService;

  public MessageController(DingTalkService dingTalkService) {
    this.dingTalkService = dingTalkService;
  }


  @GetMapping
  public String sendMsg() {
    dingTalkService.sendPushMsg();
    return "OK";
  }
}
