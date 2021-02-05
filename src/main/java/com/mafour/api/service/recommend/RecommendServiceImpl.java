package com.mafour.api.service.recommend;

import com.mafour.api.dao.dao.RecommendDO;
import com.mafour.api.dao.tunnel.RecommendTunnel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {

  @Autowired RecommendTunnel tunnel;

  @Autowired
  RecommendConverter converter;

  @Override
  public List<Recommend> listAll() {
    List<RecommendDO> all = tunnel.findAll();
    return all.stream().map(converter::converterFrom).collect(Collectors.toList());
  }
}
