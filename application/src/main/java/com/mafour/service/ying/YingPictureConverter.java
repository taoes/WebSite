package com.mafour.service.ying;

import com.mafour.dao.YingPictureDO;
import com.mafour.service.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class YingPictureConverter extends AbstractConverter<YingPicture, YingPictureDO> {

  @Override

  public YingPicture converterFrom(YingPictureDO d) {
    return new YingPicture()
        .setId(d.getId())
        .setDesc(d.getRemark())
        .setUrl(d.getUrl())
        .setKind(d.getKind());
  }

  @Override
  public YingPictureDO converterTo(YingPicture yingPicture) {
    return null;
  }
}
