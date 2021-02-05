package com.mafour.api.service.ying;

import com.mafour.api.dao.tunnel.YingPictureTunnel;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class YingPictureServiceImpl implements YingPictureService {

  private final YingPictureTunnel pictureTunnel;

  private final YingPictureConverter converter;

  public YingPictureServiceImpl(YingPictureTunnel pictureTunnel, YingPictureConverter converter) {
    this.pictureTunnel = pictureTunnel;
    this.converter = converter;
  }

  @Override
  public List<YingPicture> all(String key) {
    return pictureTunnel.all(key).stream()
        .map(converter::converterFrom)
        .collect(Collectors.toList());
  }
}
