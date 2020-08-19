package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import com.mashibing.user.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClientController {

  @Autowired
  private UserApiFeign userApi;

  @GetMapping("/getUser")
  public User getUser(String name){
    return userApi.getByName(name);
  }

}
