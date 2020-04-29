package com.mafour.service.system;

import com.mafour.common.SystemConfigKey;
import java.util.Map;
import java.util.Set;

public interface SystemService {

  Map<String, String> all();

  String getByKey(SystemConfigKey key);

  Map<String, String> getByKeys(Set<SystemConfigKey> keys);
}
