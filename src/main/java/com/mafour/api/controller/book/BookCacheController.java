package com.mafour.api.controller.book;

import com.mafour.api.service.redis.RedisService;
import java.util.HashSet;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 文章缓存相关 */
@RestController
@RequestMapping("/book/cache")
public class BookCacheController {

  @Autowired private RedisService redisService;

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
