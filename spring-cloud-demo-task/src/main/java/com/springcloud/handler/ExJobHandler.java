package com.springcloud.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @title: ExJobHandler
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-0717:36
 */


@Component
@JobHandler(value = "exjobHandler")
public class ExJobHandler extends IJobHandler {

  @Override
  public ReturnT<String> execute(String param) throws Exception {
    System.out.println(param);
    return SUCCESS;
  }
}
