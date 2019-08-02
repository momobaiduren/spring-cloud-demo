package com.springcloud.properties;

import lombok.Data;

/**
 * @author longweier
 * @version 1.0
 * @description Swagger配置
 * @date 2019/5/31 10:06
 */
@Data
public class SwaggerProperties {

    private String title;

    private String description;

    private String version;

    private boolean enable;
}
