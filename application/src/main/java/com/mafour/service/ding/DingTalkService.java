package com.mafour.service.ding;

import com.alibaba.fastjson.JSON;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DingTalkService {

  @Value("${ding.token}")
  private String token;

  private final LocalDate love = LocalDateTime.of(2017, 2, 19, 0, 0).toLocalDate();
  private final LocalDate birther = LocalDateTime.of(2020, 10, 28, 0, 0).toLocalDate();

  public void sendPushMsg() {
    String textTemp =
        "<font  style='font-weight:900;font-size:14px' color=#0088EE> 纪念日提醒 </font>\n---\n";
    textTemp += "+ 和邓影影在一起: <font  style='font-weight:bold'> %s </font> 天\n";
    textTemp += "+ 离邓影影的生日: <font  style='font-weight:bold'> %s </font> 天\n";

    LocalDate now = LocalDate.now();
    long day1 = differentDays(now, love);
    long day2 = differentDays(birther, now);

    String text = String.format(textTemp, day1, day2);

    ActionCardContent cardContent = new ActionCardContent();

    // 设置标题和内容
    cardContent.setText(text);
    cardContent.setTitle("今DingTalkMessage天纪念日提醒");

    BtnLink btnLink = new BtnLink("查看相册", "https://www.zhoutao123.com/page/ying.html");
    cardContent.setBtns(Collections.singleton(btnLink));

    DingTalkMessage message = new DingTalkMessage(cardContent);
    String content = JSON.toJSONString(message);
    NetUtils.sendDingMsg(token, content);
  }

  public void sendCommentMsf(String name, String content, String book, String slug) {
    String textTemp =
        "<font  style='font-weight:900;font-size:14px' color=#0088EE> 新的评论提醒 </font>\n---\n";
    textTemp += "+ 评论人: <font  style='font-weight:bold'> %s </font>  \n";
    textTemp += "+ 评论内容: <font  style='font-weight:bold'> %s </font> \n";

    String text = String.format(textTemp, name, content);

    ActionCardContent cardContent = new ActionCardContent();

    // 设置标题和内容
    cardContent.setText(text);
    cardContent.setTitle("新的评论提醒");

    BtnLink btnLink =
        new BtnLink("查看文章", "https://www.zhoutao123.com/page/book/" + book + "/category/" + slug);
    cardContent.setBtns(Collections.singleton(btnLink));

    DingTalkMessage message = new DingTalkMessage(cardContent);
    String msgContent = JSON.toJSONString(message);
    NetUtils.sendDingMsg(token, msgContent);
  }

  public void sendStatMsf(String bookName, String slug, Integer value) {
    String textTemp =
        "<font  style='font-weight:900;font-size:14px' color=#0088EE> 新的文章被评星 </font>\n---\n";
    textTemp += "+ bookName: <font  style='font-weight:bold'> %s </font>  \n";
    textTemp += "+ SlugName: <font  style='font-weight:bold'> %s </font> \n";
    textTemp += "+ Value: <font  style='font-weight:bold'> %s </font> \n";

    String text = String.format(textTemp, bookName, slug, value);

    ActionCardContent cardContent = new ActionCardContent();

    // 设置标题和内容
    cardContent.setText(text);
    cardContent.setTitle("新的评级提醒");

    BtnLink btnLink =
        new BtnLink("查看文章", "https://www.zhoutao123.com/page/book/" + bookName + "/category/" + slug);
    cardContent.setBtns(Collections.singleton(btnLink));

    DingTalkMessage message = new DingTalkMessage(cardContent);
    String msgContent = JSON.toJSONString(message);
    NetUtils.sendDingMsg(token, msgContent);
  }

  public static long differentDays(LocalDate firstDate, LocalDate lastDay) {
    return lastDay.until(firstDate, ChronoUnit.DAYS);
  }
}
