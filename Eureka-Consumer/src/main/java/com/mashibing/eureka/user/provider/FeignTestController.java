package com.mashibing.eureka.user.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

  @Autowired
  private API api;

  @GetMapping("/hiFeign")
  public Object hiFeign(){
    return api.getMap();
  }

}
