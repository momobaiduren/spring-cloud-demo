package com.springcloud.auto.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.auto.entity.StockInfo;
import com.springcloud.auto.mapper.StockInfoMapper;
import com.springcloud.auto.repository.StockInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
@Repository
public class StockInfoRepositoryImpl extends ServiceImpl<StockInfoMapper,StockInfo> implements
    StockInfoRepository {

}
