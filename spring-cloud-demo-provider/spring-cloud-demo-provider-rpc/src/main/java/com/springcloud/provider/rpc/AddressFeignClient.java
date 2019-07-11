package com.springcloud.provider.rpc;

import com.springcloud.auto.entity.AddressInfoTb;
import com.springcloud.provider.IAddressFeignClientApi;
import com.springcloud.service.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: AddressFeignClient
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-0616:04
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AddressFeignClient implements IAddressFeignClientApi {

  @Autowired
  private AddressService addressService;

  @Override
  public List<AddressInfoTb> getAddressList(@PathVariable(value = "addressCode")String addressCode){
    return addressService.getAddressList(addressCode);
  }
}
