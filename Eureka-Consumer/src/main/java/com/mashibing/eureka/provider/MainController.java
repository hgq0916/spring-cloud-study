package com.mashibing.eureka.provider;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletResponse;
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

  private volatile AtomicInteger selectCount = new AtomicInteger();

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
    String url = "http://EUREKA-PROVIDER"+"/hi";
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(url,String.class);
  }

  @GetMapping("/client8")
  public Object client8(){
    String url = "http://EUREKA-PROVIDER"+"/hi";
    return restTemplate.getForObject(url,String.class);
  }

  @GetMapping("/client9")
  public Object client9(){
    /**
     * 自定义负载均衡
     */
    List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-PROVIDER");

    //随机
  /*  int i = new Random().nextInt(instances.size());
    ServiceInstance serviceInstance = instances.get(i);*/
  //轮询
    int i = selectCount.incrementAndGet();
    ServiceInstance serviceInstance = instances.get(i % instances.size());
    //权重 给每个实例定义权重
    //String weight = serviceInstance.getMetadata().get("weight");
    String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/hi";
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(url,String.class);
  }

  @GetMapping("/client10")
  public Object client10(){
    String url = "http://EUREKA-PROVIDER"+"/getMap";
    Map map = restTemplate.getForObject(url, Map.class);
    return map;
  }

  @GetMapping("/client11")
  public Object client11(){
    String url = "http://EUREKA-PROVIDER"+"/getPersonByName?name={1}";
    Person person = restTemplate.getForObject(url, Person.class, "hahaha");
    return person;
  }

  @GetMapping("/client12")
  public void client12(HttpServletResponse response){
    String url = "http://EUREKA-PROVIDER"+"/postForLocation?name={1}";
    URI uri = restTemplate.postForLocation(url, null,"hello");

    try {
      response.sendRedirect(uri.toURL().toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
