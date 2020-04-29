package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.common.SystemConfigKey;
import com.mafour.dao.SystemConfigDO;
import com.mafour.mapper.SystemConfigMapper;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigTunnel extends ServiceImpl<SystemConfigMapper, SystemConfigDO> {

  public List<SystemConfigDO> findByKeys(Set<SystemConfigKey> keys) {
    LambdaQueryWrapper<SystemConfigDO> wrapper =
        new LambdaQueryWrapper<SystemConfigDO>().in(SystemConfigDO::getConfigKey, keys);
    return list(wrapper);
  }
}
