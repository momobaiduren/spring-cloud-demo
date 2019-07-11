package com.springcloud.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.auto.entity.AddressInfoTb;
import com.springcloud.auto.mapper.AddressInfoTbMapper;
import com.springcloud.service.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @title: AddressServiceImpl
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-0522:59
 */
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private AddressInfoTbMapper addressInfoTbMapper;

  @Override
  @DS("write")
  public List<AddressInfoTb> getAddress(String addressCode) {
    QueryWrapper<AddressInfoTb> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("Pid",addressCode);
    return addressInfoTbMapper.selectList(queryWrapper);
  }

  @Override
  public List<AddressInfoTb> getAddressList(String addressCode) {
    QueryWrapper<AddressInfoTb> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("Pid",addressCode);
    return addressInfoTbMapper.selectList(queryWrapper);
  }
}
