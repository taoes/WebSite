package com.mafour.api.pages;

import com.mafour.service.ying.YingPicture;
import com.mafour.service.ying.YingPictureService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page/ying.html")
public class YingPageController {

  private final YingPictureService yingPictureService;

  public YingPageController(YingPictureService yingPictureService) {
    this.yingPictureService = yingPictureService;
  }

  @GetMapping
  public String toBookContent(Model model) {
    List<YingPicture> pictures = yingPictureService.all("");
    model.addAttribute("pictureList", pictures);
    return "ying/ying";
  }
}
