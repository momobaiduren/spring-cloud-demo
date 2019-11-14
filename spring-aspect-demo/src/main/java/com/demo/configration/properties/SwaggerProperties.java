package com.demo.configration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangLong on 2019/11/4  5:33 下午
 * @version V1.0  配置
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "qianzhui")
public class SwaggerProperties {

    private String title;

    private String description;

    private String version;

    private boolean enable;
}
