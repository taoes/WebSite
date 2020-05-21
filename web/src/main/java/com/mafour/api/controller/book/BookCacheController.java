package com.mafour.api.controller.book;

import java.util.HashSet;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/cache")
public class BookCacheController {

  private final RedissonClient redissonClient;

  public BookCacheController(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
  }

  @GetMapping
  public String cacheClean(String[] cacheKey) {
    if (Arrays.isNullOrEmpty(cacheKey)) {
      return "empty";
    }
    HashSet<String> hashSet = new HashSet<String>(Lists.list(cacheKey));
    hashSet.forEach(
        key -> {
          redissonClient.getBucket(key).delete();
        });
    return "ok";
  }
}
