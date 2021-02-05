package com.mafour.api.service.system;

import com.mafour.api.common.SystemConfigKey;
import com.mafour.api.dao.dao.SystemConfigDO;
import com.mafour.api.dao.tunnel.SystemConfigTunnel;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemServiceImpl implements SystemService {

  private SystemConfigTunnel configTunnel;

  @Override
  public Map<String, String> all() {
    return null;
  }

  @Override
  public String getByKey(SystemConfigKey key) {
    return null;
  }

  @Override
  public Map<String, String> getByKeys(Set<SystemConfigKey> keys) {
    List<SystemConfigDO> configDOS = configTunnel.findByKeys(keys);

    return configDOS.stream()
        .collect(
            Collectors.toMap(config -> config.getConfigKey().name(), SystemConfigDO::getValue));
  }
}
