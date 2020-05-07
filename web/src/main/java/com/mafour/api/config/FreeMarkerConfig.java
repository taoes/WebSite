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
    configuration.setSharedVariable("domain", "https://www.zhoutao123.com");
    configuration.setSharedVariable("title", "燕归来兮");
    configuration.setSharedVariable("clientId", "4b3f1745967d3a9d09d0");
    configuration.setSharedVariable("githubCallback", "https://www.zhoutao123.com/login/github");
  }
}
