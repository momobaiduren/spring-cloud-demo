package com.demo.thread.computer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by zhanglong and since  2019/11/16  10:39 下午
 *
 * @description: 描述
 */
@Data
@AllArgsConstructor
public class ListResult<R extends MergeModel> implements MergeResult<R> {
    private List<R> rList;
}
