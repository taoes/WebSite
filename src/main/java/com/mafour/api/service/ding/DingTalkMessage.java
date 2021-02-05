package com.mafour.api.service.ding;

import lombok.Data;

@Data
public class DingTalkMessage {


  private String msgtype = "actionCard";

  private ActionCardContent actionCard;

  public DingTalkMessage(ActionCardContent actionCard) {
    this.actionCard = actionCard;
  }
}
