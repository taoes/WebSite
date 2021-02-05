package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.RecommendDO;
import com.mafour.api.dao.mapper.RecommendMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RecommendTunnel extends ServiceImpl<RecommendMapper, RecommendDO> {

  /** 查询全部推荐 */
  public List<RecommendDO> findAll() {
    return list();
  }
}
