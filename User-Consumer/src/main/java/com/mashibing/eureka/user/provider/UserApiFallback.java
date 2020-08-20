package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import java.util.Collections;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserApiFallback implements UserApiFeign{

  @Override
  public Map getMap(String id) {
    return Collections.singletonMap("id","默认id");
  }

  @Override
  public Map postMap(Map<String, Object> param) {
    return null;
  }

  @Override
  public User getUser(User user) {
    return null;
  }

  @Override
  public User getByName(String name) {
    User user = new User();
    user.setName(name);
    return user;
  }
}
