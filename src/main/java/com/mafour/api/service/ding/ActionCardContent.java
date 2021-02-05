package com.mafour.api.service.ding;

import java.util.Collection;
import lombok.Data;

@Data
public class ActionCardContent {

  private String title;

  private String text;

  private String btnOrientation = "1";

  private Collection<BtnLink> btns;
}
