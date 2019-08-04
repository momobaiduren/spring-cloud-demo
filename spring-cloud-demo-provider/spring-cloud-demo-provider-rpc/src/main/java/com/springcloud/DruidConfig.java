package com.springcloud;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: DruidConfig
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-08-0222:21
 */
@Configuration
public class DruidConfig {
  @Value("${druid.druidMappingUrl}")
  private String druidMappingUrl;
  @Value("${druid.allow}")
  private String allow;
  @Value("${druid.deny}")
  private String deny;
  @Value("${druid.loginUsername}")
  private String loginUsername;
  @Value("${druid.loginPassword}")
  private String loginPassword;
  @Value("${druid.resetEnable}")
  private String resetEnable;


  @Bean
  public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
    ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),  druidMappingUrl);
    registrationBean.addInitParameter("allow", allow);// IP白名单 (没有配置或者为空，则允许所有访问)
    registrationBean.addInitParameter("deny", deny);// IP黑名单 (存在共同时，deny优先于allow)
    registrationBean.addInitParameter("loginUsername", loginUsername);
    registrationBean.addInitParameter("loginPassword", loginPassword);
    registrationBean.addInitParameter("resetEnable", resetEnable);
    return registrationBean;
  }
}
