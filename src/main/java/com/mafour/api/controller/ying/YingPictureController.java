package com.mafour.api.controller.ying;

import com.mafour.api.service.ying.YingPicture;
import com.mafour.api.service.ying.YingPictureService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ying/picture")
public class YingPictureController {

  private final YingPictureService yingPictureService;

  public YingPictureController(YingPictureService yingPictureService) {
    this.yingPictureService = yingPictureService;
  }

  @GetMapping
  public List<YingPicture> all(@RequestParam(required = false) String type) {
    return yingPictureService.all(type);
  }
}
