##### Eureka 自我保护机制

![image-20200818165523580](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818165523580.png)

![image-20200818165949199](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818165949199.png)

关闭自我保护机制：eureka.server.enalbe-selft-preservaion:false

#### 使用Spring Root2.x Actuator监控应用

开启监控：

![image-20200818170316462](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818170316462.png)

使用/actuator查看端点信息

![image-20200818173020482](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818173020482.png)

/health/status:查看服务的健康状态

##### 开启所有的端点：

*表示所有，配置文件中添加如下：

![image-20200818173135485](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818173135485.png)

##### 远程停止微服务

management.endpoint.shutdown.enabled=true//开启可以远程关闭微服务节点

在actuator返回的结果中：新增了shutdown的地址信息

http://hgq-pc:8101/actuator/shutdown

##### 查询jvm内存

![image-20200818173439502](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818173439502.png)

##### 上报服务的真实健康状态

![image-20200818171134372](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818171134372.png)

实现HealthIndicator接口，重写health方法；

##### 测试服务的上下线

手动把服务down掉，不会立即上传，只能通过心跳上报down或者up的状态，但eureka不会剔除它，这个服务就不会被eureka下发到客户端，eureka只会把up的服务下发给客户端

只要发心跳包，不管是up或者down，但不会被eureka剔除，只是修改节点的状态

#####　开启eureka 安全连接

在eureka中引入spring security依赖包，

```xml
<dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-web</artifactId>
    </dependency>
```



然后配置用户名密码：

![image-20200818174845849](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818174845849.png)

此时服务提供方无法访问eureka,

需要关闭eureka的csrf攻击，

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    super.configure(http);
  }
}
```



请求链接带上用户名密码

**http://username:password@localhost:8000/eureka/**

![image-20200818175950529](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818175950529.png)

####　两种负载均衡

　　　ribbon是基于客户端的负载均衡

Nginx:实现服务端的负载均衡：

![image-20200818182158657](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818182158657.png)

LoadBalancer:ZoneAareLoadBalancer

##### 自定义负载均衡策略

负载均衡策略：hash、轮询、随机、权重

1.实现IRule接口

2.通过获取DiscoveryClient，自定义负载均衡策略

3.在配置文件定义负载均衡策略

![image-20200818201800712](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818201800712.png)

##### Ribbon脱离Eureka

![image-20200818202052483](04 Ribbon 客户端的负载均衡 服务手动上下线.assets/image-20200818202052483.png)

##### 使用服务名称调用服务

作业：

使用RestTemplate发起远程调用

##### jrebel安装

热部署工具

