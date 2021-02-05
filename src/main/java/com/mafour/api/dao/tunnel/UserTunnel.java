package com.mafour.api.dao.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.api.dao.dao.UserDO;
import com.mafour.api.dao.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserTunnel extends ServiceImpl<UserMapper, UserDO> {}
