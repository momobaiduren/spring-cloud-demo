package com.springcloud.properties;

import lombok.Data;

/**
 * @title: SwaggerProperties
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-08-1100:20
 */
@Data
public class SwaggerProperties {

    private String title;

    private String description;

    private String version;

    private boolean enable;
}
