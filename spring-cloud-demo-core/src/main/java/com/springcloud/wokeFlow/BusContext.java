package com.springcloud.wokeFlow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lizhou on 2017/5/10/010.
 */
public class BusContext {
    Map<Station, Class<BusWorkFlowStationHandler>> context = new ConcurrentHashMap<>();

}
