package com.mafour.api.controller.book;

import com.mafour.api.service.redis.RedisService;
import java.util.HashSet;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/cache")
public class BookCacheController {

  private final RedisService redisService;

  public BookCacheController(RedisService redisService) {
    this.redisService = redisService;
  }

  @GetMapping
  public String cacheClean(String[] cacheKey) {
    if (Arrays.isNullOrEmpty(cacheKey)) {
      return "empty";
    }
    HashSet<String> hashSet = new HashSet<String>(Lists.list(cacheKey));
    hashSet.forEach(redisService::del);
    return "ok";
  }
}
