package com.springcloud.configuration;

import com.springcloud.global.GlobalManager;
import lombok.Data;
;

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

}
