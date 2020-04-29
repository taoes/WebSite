package com.mafour.tunnel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.UserDO;
import com.mafour.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserTunnel extends ServiceImpl<UserMapper, UserDO> {}
