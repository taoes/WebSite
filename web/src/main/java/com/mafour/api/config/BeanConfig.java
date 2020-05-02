package com.mafour.api.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BeanConfig {

  @Bean
  public RedissonClient getRedisson() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    RedissonClient redissonClient = Redisson.create(config);
    log.info("redis client is ok.....");
    return redissonClient;
  }
}
