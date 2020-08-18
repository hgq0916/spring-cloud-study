package com.mashibing.eureka.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @Autowired
  private ProviderHealthService providerHealthService;

  @GetMapping("/hi")
  public String hi(){
    return "hi,I'm provider";
  }


  @GetMapping("/status")
  public String status(Boolean status){
    providerHealthService.setStatus(status);
    return "ok";
  }

}
