# springcloud实战 作者:zhanglong 
基于springcloud的一整套单机版配置后期仍然会做更新，敬请期待哦
如果用问题请在 issues中进行提问哦，第一时间尽快解决，或者问题发到个人邮箱
## 在spring-cloud-demo的pom中分为以下结构
...
 <modules>
    注册中心
    <module>spring-cloud-demo-eureka</module>
    配置中心
    <module>spring-cloud-demo-config</module>
    网关
    <module>spring-cloud-demo-gateway</module>
    xxl调度中心
    <module>spring-cloud-demo-task</module>
    提供方APi
    <module>spring-cloud-demo-api</module>
    实体类
    <module>spring-cloud-demo-persist</module>
    表现层暴露到网关
    <module>spring-cloud-demo-provider</module>
    业务2处理
    <module>spring-cloud-demo-service</module>
    公共类、核心类
    <module>spring-cloud-demo-common</module>
    数据源读写分离
    <module>spring-cloud-demo-datasource</module>
    spring Tcc @Around 样例
    <module>spring-aspect-demo</module>
    模块1业务类
    <module>spring-cloud-demo-service1</module>
    阿里云蚂蚁金服开源分布式事务框架
    <module>spring-cloud-demo-seata</module>
  </modules>
 ...
 * 1.注册中心使用Eureka
 * 2.配置中心使用config 配置动态更新使用的bus+rabiitmq（消息总线）
 * 3.网关使用zuul
 * 4.调度中心使用携程xxl
  ...
    <modules>
      xxl的核心类
      <module>xxl-job-core</module>
      xxl的handler调度中心管理器
      <module>xxl-job-admin</module>
      xxl的job
      <module>xxl-job-executor-samples</module>
    </modules>
  ...
  * 5.对外提供的api 配置feignclient的restful配置包含hystrix断路器，来进行对应接口流量监控，服务降级，熔断降级
  * 6.DAO实体类操作生成
  * 7.提供方
  ...
    <modules>
     系统内对外提供远程调用服务
     <module>spring-cloud-demo-provider-rpc</module>
     提供本地服务数据业务对外开发
     <module>spring-cloud-demo-provider-ba</module>
    </modules>
  ...
 * 8.业务2业务层实现
 * 9.公共类全局统一utils工具以及一些封装函数方法
 * 10.数据源目前使用mybatis-plus的动态数据源进行读写分离，后期会研究加入mycat分表分库、读写分离组件
 * 11.一些样例编写使用（自己练习使用）
 * 12.业务1业务层实现
 * 13.蚂蚁金服开源分布式事务框架样例(目前正在研究源码过程中，后期会添加注释分享给大家哦)
 ...
  <modules>
    订单模块
    <module>spring-cloud-demo-seata-order</module>
    业务处理模块
    <module>spring-cloud-demo-seata-business</module>
    库存模块
    <module>spring-cloud-demo-seata-storage</module>
    账户资金模块
    <module>spring-cloud-demo-seata-account</module>
  </modules>
 ...
    
