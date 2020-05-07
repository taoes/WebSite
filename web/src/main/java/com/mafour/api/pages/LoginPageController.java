package com.mafour.api.pages;

import com.mafour.service.github.GithubUser;
import com.mafour.service.utils.HttpUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginPageController {

  public LoginPageController() {
    System.out.println();
  }

  @GetMapping("/github")
  public void githubLogin(
      @RequestParam String code, HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();

    // 查询github
    GithubUser githubUserByCode = HttpUtils.getGithubUserByCode(code);
    log.info("获取到Github信息:{}", githubUserByCode);

    session.setAttribute("name", githubUserByCode.getName());
    session.setAttribute("email", githubUserByCode.getEmail());
    session.setAttribute("blog", githubUserByCode.getBlog());
    session.setAttribute("avatar_url", githubUserByCode.getAvatar_url());
    try {
      response.sendRedirect("/");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/github/exit")
  public void exit(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    session.removeAttribute("name");
    session.removeAttribute("email");
    session.removeAttribute("blog");
    session.removeAttribute("avatar_url");
    try {
      response.sendRedirect("/");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
