package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import com.mashibing.user.api.UserApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

  @Override
  public User getByName(String name) {
    return new User(name,30);
  }

}
