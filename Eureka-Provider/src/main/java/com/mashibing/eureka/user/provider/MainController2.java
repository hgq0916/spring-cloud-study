package com.mashibing.eureka.user.provider;

import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController2 {

  @Autowired
  private ProviderHealthService providerHealthService;

  @GetMapping("/getMap")
  public Map getMap(){
    return Collections.singletonMap("name","张三丰");
  }

  @GetMapping("/getPersonByName")
  public Person getPerson(String name){
    return new Person(name,18);
  }

  @PostMapping("/postForLocation")
  public void postForLocation(String name, HttpServletResponse response){

    String url = "http://www.baidu.com?u="+name;
    response.addHeader("Location",url);
  }


}
