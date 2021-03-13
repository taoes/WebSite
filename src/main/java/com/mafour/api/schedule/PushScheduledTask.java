package com.mafour.api.schedule;

import com.mafour.api.dao.tunnel.BookContentTunnel;
import com.mafour.api.dao.tunnel.BookTunnel;
import com.mafour.api.service.seo.SeoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PushScheduledTask {

  @Autowired private BookContentTunnel contentTunnel;

  @Autowired private BookTunnel bookTunnel;

  @Autowired private SeoService seoService;

  /** 百度SEO定时推送 */
  @Scheduled(cron = "0 0 6,12,16,23 * * ?")
  public void scheduleSeoPushForBaidu() {
    log.info("触发定时SEO 图书 & 文章推送");
    Map<String, String> slugMap = contentTunnel.findBookContentLink();
    if (CollectionUtils.isEmpty(slugMap)) {
      log.warn("查询文章信息列表为空，跳过SEO文章推送!");
      return;
    }

    for (Map.Entry<String, String> slugInfo : slugMap.entrySet()) {
      seoService.push(slugInfo.getValue(), slugInfo.getKey());
    }
    log.info("推送文章链接 SEO 完成,推送个数:{}", slugMap.size());

    // 推送图书连接
    List<String> bookIds = bookTunnel.allId(String::valueOf);
    if (CollectionUtils.isEmpty(bookIds)) {
      log.warn("查询图书信息列表为空，跳过SEO图书推送!");
      return;
    }

    bookIds.forEach(seoService::push);
    log.info("推送图书连接 SEO 完成, 推送个数:{}", bookIds.size());
  }
}
