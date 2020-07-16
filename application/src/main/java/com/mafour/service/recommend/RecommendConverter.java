package com.mafour.service.recommend;

import com.mafour.dao.RecommendDO;
import com.mafour.service.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RecommendConverter extends AbstractConverter<Recommend, RecommendDO> {

  @Override
  public Recommend converterFrom(RecommendDO d) {
    return null;
  }

  @Override
  public RecommendDO converterTo(Recommend recommend) {
    return null;
  }
}
