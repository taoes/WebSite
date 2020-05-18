package com.mafour.api.config;

import com.mafour.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler(NotFoundException.class)
  public HttpEntity<String> handle(NotFoundException e, Model model) {
    log.error("发生异常,尝试访问的页面资源不存在:", e);
    model.addAttribute("code", "404");
    model.addAttribute("msg", e.getMessage());
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RuntimeException.class)
  public String handle(RuntimeException e, Model model) {
    log.error("发生异常:", e);
    model.addAttribute("msg", e.getMessage());
    model.addAttribute("code", "404");
    return "base/500";
  }
}
