package com.mashibing.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gangquan.hu
 * @Package: com.mashibing.user.api.UserApi
 * @Description: TODO
 * @date 2020/8/19 14:59
 */
@RequestMapping("/user")
public interface UserApi {

  @GetMapping("/getByName")
  public User getByName(@RequestParam(name="name") String name);

}
