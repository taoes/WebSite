package com.mafour.api.config;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreeMarkerConfig {

  private freemarker.template.Configuration configuration;

  public FreeMarkerConfig(freemarker.template.Configuration configuration) {
    this.configuration = configuration;
  }

  @PostConstruct
  public void setConfigure() throws Exception {
    configuration.setSharedVariable("base", "localhost:9999");
    configuration.setSharedVariable("title", "码否-专业的技术论坛");
  }
}
