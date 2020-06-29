package com.mafour.api.schedule;


import com.mafour.service.ding.DingTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DayTipsSchedule {

  private final DingTalkService dingTalkService;

  public DayTipsSchedule(DingTalkService dingTalkService) {
    this.dingTalkService = dingTalkService;
  }

  @Scheduled(cron = "0 0 8 * * *")
  public void string(){
    log.info("发送定时消息");
    dingTalkService.sendPushMsg();
  }



}
