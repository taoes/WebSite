package com.mafour.service.info;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class PersonInfo {

  private String icon;

  private String title;

  private String url;
}
