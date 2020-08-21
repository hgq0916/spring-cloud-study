package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import com.mashibing.user.api.UserApi;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserInfoApi extends UserApi {

  @GetMapping("/getMap")
  public Map getMap(String id);

  @PostMapping("/postMap")
  public Map postMap(@RequestParam Map<String,Object> param);

  @GetMapping("/api/getUser")
  public User getUser(User user);

}
