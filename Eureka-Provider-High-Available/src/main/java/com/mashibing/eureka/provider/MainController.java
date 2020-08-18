package com.mashibing.eureka.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/hi")
  public String hi(){
    return "hi,I'm high available provider";
  }

}
