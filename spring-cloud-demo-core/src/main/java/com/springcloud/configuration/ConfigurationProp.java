package com.springcloud.configuration;

import com.springcloud.global.GlobalManager;
import com.springcloud.properties.SwaggerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @title:
 * @projectName spring-cloud-demo
 * @description:
 * @author zhanglong
 * @date 2019-08-0200:27
 */

@Data
@Configuration
@ConfigurationProperties(prefix = GlobalManager.ROOT_PREFIX)
public class ConfigurationProp {

  private SwaggerProperties swagger;

}
