package com.mashibing.eureka.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.mashibing.eureka.user.provider")
@EnableEurekaClient
@SpringBootApplication
public class UserConsumerApplication {

  @Bean
  @LoadBalanced//实现负载均衡
  public RestTemplate restTemplate(){
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add(new MyClientHttpRequestInterceptor());
    return restTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(UserConsumerApplication.class,args);
  }

  /**
   * 自定义rule实现负载均衡
   * @return
   */
/*  @Bean
  IRule getRule(){
    //return new RoundRobinRule();
    return new RandomRule();
  }*/

}
