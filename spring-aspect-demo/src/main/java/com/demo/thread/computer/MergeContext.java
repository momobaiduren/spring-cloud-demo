package com.demo.thread.computer;

import lombok.Data;

/**
 * created by zhanglong and since  2019/11/17  1:07 上午
 *
 * @description: 描述
 */
@Data
class MergeContext<M extends MergeModel,H extends MergeHandler, R> {
    private Class<M> mClass;

    private H MergeHandler;

    private Class<R> rClass;

}
