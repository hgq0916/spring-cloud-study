package com.mashibing.eureka.provider;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private EurekaClient eurekaClient;
  @Autowired
  private LoadBalancerClient loadBalancerClient;//负载均衡客户端
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/hi")
  public String hi(){
    return "hi,I'm provider";
  }

  @GetMapping("/client")
  public Object client(){
    List<String> services = discoveryClient.getServices();
    return services;
  }

  @GetMapping("/client2")
  public Object client2(){
    List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-PROVIDER");
    return instances;
  }

  @GetMapping("/client3")
  public Object client3(){
    List<InstanceInfo> instances = eurekaClient
        .getInstancesByVipAddress("EUREKA-PROVIDER", false);

    return instances;
  }

  @GetMapping("/client4")
  public Object client4(){
    List<InstanceInfo> instances = eurekaClient.getInstancesById("hgq-pc:Eureka-provider:8101");
    return instances;
  }

  @GetMapping("/client5")
  public Object client5(){
    List<InstanceInfo> instances = eurekaClient.getInstancesById("hgq-pc:Eureka-provider:8101");
    if(instances.size()>0){
      InstanceInfo instanceInfo = instances.get(0);
      if(instanceInfo.getStatus().equals(InstanceStatus.UP)){
        //应用是可用的
        String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()+"/hi";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url,String.class);
      }
    }
    return instances;
  }

  @GetMapping("/client6")
  public Object client6(){
    ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-PROVIDER");
    String instanceId = serviceInstance.getInstanceId();
    System.out.println("instanceId:"+instanceId);
    String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/hi";
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(url,String.class)+" instanceId:"+instanceId;
  }

  @GetMapping("/client7")
  public Object client7(){
    String url = "http://localhost:8101"+"/hi";
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(url,String.class);
  }

}
