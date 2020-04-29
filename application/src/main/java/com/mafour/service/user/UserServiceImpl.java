package com.mafour.service.user;

import com.mafour.service.user.model.JwtToken;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Override
  public JwtToken login(String username, String password) {
    return JwtToken.valueOf(UUID.randomUUID().toString());
  }
}
