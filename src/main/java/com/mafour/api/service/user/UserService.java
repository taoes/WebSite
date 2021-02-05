package com.mafour.api.service.user;

import com.mafour.api.service.user.model.JwtToken;

public interface UserService {

  JwtToken login(String username, String password);
}
