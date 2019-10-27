package com.springcloud.configuration;

import com.springcloud.properties.SwaggerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author  zhanglong on 2019-08-02  3:07 下午
 * @version V1.0 配置约定的配置文件对应前缀
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "ss.ss")
public class ConfigurationProp {

  private SwaggerProperties swagger;

}
