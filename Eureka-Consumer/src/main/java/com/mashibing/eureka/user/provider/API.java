package com.mashibing.eureka.user.provider;

import java.util.Map;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gangquan.hu
 * @Package: API
 * @Description: TODO
 * @date 2020/8/19 14:32
 */
@FeignClient(name = "test",url = "http://localhost:8101")
public interface API {

  @GetMapping("/getMap")
  public Map getMap();

}
