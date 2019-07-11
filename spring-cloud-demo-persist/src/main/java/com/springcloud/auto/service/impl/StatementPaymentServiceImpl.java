package com.springcloud.auto.service.impl;

import com.springcloud.auto.entity.StatementPayment;
import com.springcloud.auto.mapper.StatementPaymentMapper;
import com.springcloud.auto.service.IStatementPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-11
 */
@Service
public class StatementPaymentServiceImpl extends ServiceImpl<StatementPaymentMapper, StatementPayment> implements IStatementPaymentService {

}
