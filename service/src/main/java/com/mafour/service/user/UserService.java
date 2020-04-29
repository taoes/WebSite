package com.mafour.service.user;

import com.mafour.service.user.model.JwtToken;
import java.util.List;

public interface UserService {

  JwtToken login(String username, String password);
}
