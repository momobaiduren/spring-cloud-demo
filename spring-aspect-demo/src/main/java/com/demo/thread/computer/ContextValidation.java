package com.demo.thread.computer;

import java.util.Objects;

/**
 * created by zhanglong and since  2019/11/17  3:16 上午
 *
 * @description: 描述
 */
public final class ContextValidation {
    public static boolean validate(ShardingContext shardingContext){
        if(Objects.isNull(shardingContext.getHandlerClass())) {
            return false;
        }
        if(Objects.isNull(shardingContext.getRClass())) {
            return false;
        }
        if(Objects.isNull(shardingContext.getShardingHander())) {
            return false;
        }

        return true;
    }
}
