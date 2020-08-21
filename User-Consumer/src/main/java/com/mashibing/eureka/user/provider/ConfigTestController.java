package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.User;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigTestController {

  @Value("${myconfig}")
  private String myconfig;

  @GetMapping
  public String config(){
    return myconfig;
  }

}
