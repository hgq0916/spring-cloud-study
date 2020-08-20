package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "back")
  public User getUser(User user){

    String url = "http://User-Provider/user/getUser?name={name}&age={age}";
    Map<String,Object> map = new HashMap<>();
    map.put("name",user.getName());
    map.put("age",user.getAge());
    ResponseEntity<User> forEntity = restTemplate.getForEntity(url, User.class, map);
    return forEntity.getBody();
  }

  public User back(User user1){
    User user = new User();
    user.setName("默认用户");
    return user;
  }

}
