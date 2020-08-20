package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClientController {

  @Autowired
  private UserApiFeign userApi;

  @GetMapping("/getUser")
  public User getUser(String name){
    return userApi.getByName(name);
  }

  @GetMapping("/getMap")
  public Map getMap(String id){
    return userApi.getMap(id);
  }

  @GetMapping("/postMap")
  public Map postMap(@RequestParam  Map<String,Object> param){

    return userApi.postMap(param);
  }

  @GetMapping("/getUser2")
  public User getUser(User user){
    return userApi.getUser(user);
  }

}
