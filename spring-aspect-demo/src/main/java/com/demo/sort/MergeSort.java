package com.demo.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhanglong
 * @Date 2019-09-13  18:16
 * @Version V1.0
 * 归并排序
 */
public class MergeSort {

    public static <T> void mergeSort(boolean isAsc, int begainIndex, int endIndex, T ... ts){
        for (int i = begainIndex; i < endIndex; i++) {
            T t;
            for (int j = begainIndex+1; j < endIndex; j++) {
                if (!ComparatorUtil.comparate(ts[i],ts[j],isAsc)) {
                    t = ts[i];
                    ts[i] = ts[j];
                    ts[j] = t;
                }
            }
        }
    }

    public static <T> List<T> mergeSortOrderBySharding(boolean isAsc, int begainIndex, int endIndex, int shardingNum, T ... ts){
       int index = sharding(begainIndex,endIndex,shardingNum);
        for (int i = endIndex; i < begainIndex; i=endIndex-index) {
            mergeSort(isAsc, endIndex-index, i, ts);
        }
        System.out.println(Arrays.asList(ts));
        return Arrays.asList(ts);
    }

    private static int sharding(int begainIndex, int endIndex, int shardingNum){
        if ((endIndex - begainIndex) == 1){
            return endIndex - begainIndex;
        }
        return (endIndex - begainIndex)/shardingNum;
    }

    public static void main(String[] args) {
        mergeSortOrderBySharding(true, 0, 9,3,"3","1","2","3","1","2","3","1","2","3","1","2","3","1","2");
    }

}
