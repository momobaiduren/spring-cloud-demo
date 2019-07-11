package com.springcloud.provider.hystrix;

import com.springcloud.auto.entity.AddressInfoTb;
import com.springcloud.provider.IAddressFeignClientApi;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @title: AddressFeignHystrix
 * @projectName spring-cloud-demo
 * @description: 断路器
 * @author zhanglong
 * @date 2019-07-0616:11
 */
//@Primary
@Component
public class AddressFeignHystrix implements IAddressFeignClientApi {
  @Override
  public List<AddressInfoTb> getAddressList(String addressCode) {

    //返回兜底数据
    return null;
  }
}
