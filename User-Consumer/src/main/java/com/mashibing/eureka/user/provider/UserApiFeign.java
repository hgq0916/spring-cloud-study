package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import com.mashibing.user.api.UserApi;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="User-Provider",fallback = UserApiFallback.class)
public interface UserApiFeign extends UserApi {

  @GetMapping("/getMap")
  public Map getMap(@RequestParam(name = "id") String id);

  @PostMapping("/postMap")
  public Map postMap(@RequestParam Map<String,Object> param);

  @GetMapping("/getUser")
  public User getUser(@SpringQueryMap User user);

}
