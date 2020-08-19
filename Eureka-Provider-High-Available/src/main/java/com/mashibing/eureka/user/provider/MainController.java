package com.mashibing.eureka.user.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @Value("${server.port}")
  private String port;

  @GetMapping("/hi")
  public String hi(){
    return "hi,I'm high available provider,port:"+port;
  }

}
