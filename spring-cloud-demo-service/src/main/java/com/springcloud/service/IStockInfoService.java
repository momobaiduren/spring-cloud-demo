package com.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.auto.entity.StockInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
public interface IStockInfoService {

  StockInfo jianStock(StockInfo stockInfo);
}
