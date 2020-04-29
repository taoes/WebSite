package com.mafour.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.GithubDO;
import com.mafour.mapper.GithubMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GithubTunnel extends ServiceImpl<GithubMapper, GithubDO> {

  public List<GithubDO> all() {
    return list();
  }
}
