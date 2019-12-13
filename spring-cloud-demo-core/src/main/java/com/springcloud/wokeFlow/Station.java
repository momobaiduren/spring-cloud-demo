package com.springcloud.wokeFlow;

/**
 * @author ZhangLong on 2019/11/23  9:50 下午
 * @version V1.0
 */
public interface Station {
    String stationFlag();

    Integer ordering();

    String desc();

    Station[] beforeStations();

    Station[] afterStations();
}
