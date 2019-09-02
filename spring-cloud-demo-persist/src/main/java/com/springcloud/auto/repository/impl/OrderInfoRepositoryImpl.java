package com.springcloud.auto.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.auto.entity.OrderInfo;
import com.springcloud.auto.mapper.OrderInfoMapper;
import com.springcloud.auto.repository.OrderInfoRepository;
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
public class OrderInfoRepositoryImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements
    OrderInfoRepository {

}
