package com.springcloud.excle;

import static java.util.stream.Collectors.groupingBy;

import com.springcloud.auto.repository.OrderInfoRepository;
import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;
import com.springcloud.excle.importModel.OrderModel;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author zhanglong
 * @description: 业务处理（多表进行分组处理）
 * @date 2019-09-0218:54
 */
@Service
public class OrderInfoEasyExcelHandler extends AbstractEasyExcelHandler<OrderModel> {

    private OrderInfoRepository orderInfoRepository;


    @Override
    public void handlerData( DataHandler<OrderModel> dataHandler ) {
        List<OrderModel> orderModels = dataHandler.get();
        Map<String, List<OrderModel>> collect = orderModels.stream()
            .collect(groupingBy(OrderModel::getOrderNo));
    }
}
