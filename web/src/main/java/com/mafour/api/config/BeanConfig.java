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
    String address = "redis://redis-service:6379";
    log.info("Redis 地址:{}", address);
    Config config = new Config();
    config
        .useSingleServer()
        .setAddress(address)
        .setTimeout(100000)
        .setConnectionMinimumIdleSize(10);
    RedissonClient redissonClient = Redisson.create(config);
    log.info("Redis 客户端配置成功.....");
    return redissonClient;
  }
}
