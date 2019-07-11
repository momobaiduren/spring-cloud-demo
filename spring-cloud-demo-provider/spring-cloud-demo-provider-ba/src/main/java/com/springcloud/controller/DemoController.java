package com.springcloud.controller;

import com.springcloud.auto.entity.AddressInfoTb;
import com.springcloud.provider.IAddressFeignClientApi;
import com.springcloud.service.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: DemoController
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-0522:20
 */

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoController {
  @Autowired
  private AddressService addressService;
  @GetMapping("/getAddress/{addressCode}")
  public List<AddressInfoTb> getAddress(@PathVariable(value = "addressCode") String addressCode){
    return addressService.getAddress(addressCode);
  }
//  @Autowired
//  private IAddressFeignClientApi iAddressFeignClientApi;
//
//  @RequestMapping("/getAddressList/{addressCode}")
//  public List<AddressInfoTb> getAddressList(@PathVariable(value = "addressCode") String addressCode){
//    return iAddressFeignClientApi.getAddressList(addressCode);
//  }

}
