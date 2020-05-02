package com.mafour.api.config;

import com.mafour.exception.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler(NotFoundException.class)
  public String handle(NotFoundException e, Model model) {
    model.addAttribute("code", "404");
    model.addAttribute("msg", e.getMessage());
    return "base/404";
  }

  @ExceptionHandler(RuntimeException.class)
  public String handle(RuntimeException e, Model model) {
    model.addAttribute("msg", e.getMessage());
    model.addAttribute("code", "404");
    return "base/500";
  }
}
