package com.mafour.api.service.info;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonInfo {

  private String icon;

  private String title;

  private String url;
}
