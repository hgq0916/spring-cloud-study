Netflix Eureka 2.0停止维护了

Nacos、zookeeper替代

#### SpringCloud 全家桶 Hoxton SR3

![image-20200817204858941](02 SpringCloud 全家桶所有组 需求拆解 HttpDns 二级域名 CDN系统.assets/image-20200817204858941.png)

WAF:网络应用防火墙，可以过滤没意义的请求或恶意请求

Nginx:一般支撑1-2万个并发，单机可以支撑的并发量跟cpu、内存的性能关系并不大，可以支撑的并发量跟用户请求产生的带宽有关系

10M的带宽：抗1万个连接，数据包很小的情况下可能

用户的请求：一个请求数据包1M，1万个Qps(吞吐量)，需要1万M的带宽，1万M的网卡，单机不可能跑这么高的带宽；

Kong

LVS+Keepalived :机房内负载均衡器，一个机房就一个机房内负载均衡器

如果机房内负载均衡器不够用，使用多个机房，要保证数据一致性

LVS由DNS来做负载均衡，根据ip请求不同的服务器

机房数据同步：

​	1.机房直连

Tps(有状态的,)，Qps(静态资源)

QPS:峰值时间每秒请求数

TPS:服务器每秒处理的事务数

HttpsDNS:防劫持

##### 域名，二级域名

范域名：*
买个域名：阿里云

webflux:基于netty；

游戏服务器：Netty

![image-20200817221737106](02 SpringCloud 全家桶所有组 需求拆解 HttpDns 二级域名 CDN系统.assets/image-20200817221737106.png)

![image-20200817221749515](02 SpringCloud 全家桶所有组 需求拆解 HttpDns 二级域名 CDN系统.assets/image-20200817221749515.png)

热部署：DevTools/jrebel

任务调度：quartz、xxl-job

模块多或者并发高：使用springcloud，否则使用单体应用更好

中台：自助建站系统

#### 搭建Eurake 注册中心

Eurake集群：很容易出现节点信息不一致的现象

作业：搭建Eurake集群

#### Restful api

第一级别：Http调json

第二级别：引入实体对象或者资源的概念

第三级别：用不同的http的方法调用

第四级别：增加Api版本控制

第五级别：SpringData Rest

