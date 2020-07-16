package com.mafour.service.recommend;

import com.mafour.dao.RecommendDO;
import com.mafour.service.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RecommendConverter extends AbstractConverter<Recommend, RecommendDO> {

  @Override
  public Recommend converterFrom(RecommendDO d) {
    return new Recommend()
        .setId(d.getId())
        .setName(d.getName())
        .setCover(d.getCover())
        .setLink(d.getLink())
        .setRemark(d.getRemark())
        .setType(d.getType());
  }

  @Override
  public RecommendDO converterTo(Recommend recommend) {
    return new RecommendDO()
        .setId(recommend.getId())
        .setName(recommend.getName())
        .setCover(recommend.getCover())
        .setLink(recommend.getLink())
        .setRemark(recommend.getRemark())
        .setType(recommend.getType());
  }
}
