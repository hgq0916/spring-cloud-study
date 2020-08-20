package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import com.mashibing.user.api.UserApi;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserInfoApi {

  @Override
  public User getByName(String name) {
    return new User(name,30);
  }

  @Override
  public Map getMap(String id) {
    return Collections.singletonMap(id,"hello world");
  }

  @Override
  public Map postMap(Map<String, Object> param) {
    System.out.println(param);
    return Collections.singletonMap("sdfsdfsdfsdf","ok");
  }

  @Override
  public User getUser(User user) {
    System.out.println(ToStringBuilder.reflectionToString(user));
    user.setAge(100);
    return user;
  }

}
