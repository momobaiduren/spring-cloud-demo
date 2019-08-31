package com.demo.proxy;

import java.util.Date;

/**
 * @author zhanglong
 * @title: LogManager
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-1909:18
 */

public interface LogManager {

    String loginfo( String createName, Date createTime );
}
