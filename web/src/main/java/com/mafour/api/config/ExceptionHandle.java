package com.mafour.api.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler(RuntimeException.class)
  public String handle(RuntimeException e, Model model) {
    model.addAttribute("msg", e.getMessage());
    return "base/404";
  }
}
