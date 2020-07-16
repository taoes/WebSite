package com.mafour.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.RecommendDO;
import com.mafour.mapper.RecommendMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RecommendTunnel extends ServiceImpl<RecommendMapper, RecommendDO> {

  /** 查询全部推荐 */
  public List<RecommendDO> findAll() {
    return list();
  }
}
