package com.mashibing.eureka.provider;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class EurekaConsumerApplication {

  @Bean
  @LoadBalanced//实现负载均衡
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(EurekaConsumerApplication.class,args);
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
