package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.GithubDO;
import com.mafour.api.dao.mapper.GithubMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GithubTunnel extends ServiceImpl<GithubMapper, GithubDO> {

  public List<GithubDO> all() {
    return list();
  }
}
