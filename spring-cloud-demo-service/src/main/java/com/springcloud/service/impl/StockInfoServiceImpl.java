package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.auto.entity.StockInfo;
import com.springcloud.auto.mapper.StockInfoMapper;
import com.springcloud.datasource.annotation.WriteDS;
import com.springcloud.service.IStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
@Service
public class StockInfoServiceImpl implements IStockInfoService {
  @Autowired
  private StockInfoMapper stockInfoMapper;
  @Override
  @WriteDS
  public StockInfo jianStock(StockInfo stockInfo) {
    QueryWrapper ew = new QueryWrapper();
    ew.eq("product_Id",stockInfo.getProductId());
    StockInfo proStockInfo = stockInfoMapper.selectOne(ew);
    proStockInfo.setStockNum(proStockInfo.getStockNum() - stockInfo.getStockNum());
    stockInfoMapper.update(proStockInfo,ew);
    return proStockInfo;
  }
}
