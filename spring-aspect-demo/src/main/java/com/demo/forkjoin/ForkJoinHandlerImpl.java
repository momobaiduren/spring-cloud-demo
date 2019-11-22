package com.demo.forkjoin;

import java.util.Objects;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午
 *
 * @description: 描述
 */
public class ForkJoinHandlerImpl  implements ForkJoinHandler{

    @Override
    public int count(Objects conditions) {
        return 1000;
    }

    @Override
    public void execute(int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.println(start);
        }
    }
}
