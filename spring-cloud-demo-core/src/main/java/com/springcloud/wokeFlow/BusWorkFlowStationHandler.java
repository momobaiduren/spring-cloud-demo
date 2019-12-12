package com.springcloud.wokeFlow;

/**
 * @author ZhangLong on 2019/11/24  9:59 上午
 * @version V1.0
 */
public interface BusWorkFlowStationHandler {
    void handlerBusContext(Class<BusContext> busContextClass);
}
