package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import feign.hystrix.FallbackFactory;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class WebError implements FallbackFactory<UserApiFeign> {

  @Override
  public UserApiFeign create(Throwable throwable) {
    return new UserApiFeign() {
      @Override
      public Map getMap(String id) {
        return null;
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
        throwable.printStackTrace();
        User user = new User();
        user.setName(throwable.getMessage());
        return user;
      }
    };
  }
}
