package com.springcloud.service;

import com.springcloud.auto.entity.AddressInfoTb;
import java.util.List;

/**
 * @title: AddressService
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-0522:58
 */

public interface AddressService {

  List<AddressInfoTb> getAddress(String addressCode);

  List<AddressInfoTb> getAddressList(String addressCode);
}
