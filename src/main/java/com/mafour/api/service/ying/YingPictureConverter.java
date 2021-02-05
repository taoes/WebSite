package com.mafour.api.service.ying;

import com.mafour.api.service.AbstractConverter;
import com.mafour.api.dao.dao.YingPictureDO;
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
