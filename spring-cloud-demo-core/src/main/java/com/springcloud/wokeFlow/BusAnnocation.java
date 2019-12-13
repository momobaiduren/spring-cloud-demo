package com.springcloud.wokeFlow;

/**
 * @author ZhangLong on 2019/11/23  3:42 下午
 * @version V1.0
 */
public @interface BusAnnocation {
    String presentStation();

    Class<BusWorkFlowStationContext> busWorkFlowStations();
}
