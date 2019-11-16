package com.demo.thread.computer;

import java.util.Map;
import lombok.Data;

/**
 * created by zhanglong and since  2019/11/16  10:39 下午
 *
 * @description: 描述
 */
@Data
public class MapResult<R extends MergeModel> implements MergeResult<R> {
    private Map<Object, R> rMap;
}
