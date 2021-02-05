package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.YingPictureDO;
import com.mafour.api.dao.mapper.YingPictureMapper;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class YingPictureTunnel extends ServiceImpl<YingPictureMapper, YingPictureDO> {

  public List<YingPictureDO> all(String key) {
    Wrapper<YingPictureDO> wrapper =
        new LambdaQueryWrapper<YingPictureDO>()
            .like(StringUtils.hasText(key), YingPictureDO::getKind, key)
            .orderByDesc(YingPictureDO::getKind)
            .orderByDesc(YingPictureDO::getOrdinal);
    return this.list(wrapper);
  }
}
