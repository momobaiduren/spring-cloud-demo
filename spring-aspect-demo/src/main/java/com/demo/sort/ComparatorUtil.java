package com.demo.sort;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * @Author zhanglong
 * @Date 2019-09-14  10:13
 * @Version V1.0
 */
public class ComparatorUtil {

    public static <T> T returnBefore(T arg1, T arg2, boolean isMax){
        int compare = ComparatorUtils.NATURAL_COMPARATOR.compare(arg1, arg2);
        if (isMax){
            if (compare > 0){
                return arg1;
            }else{
                return arg2;
            }
        }else {
            if (compare < 0){
                return arg1;
            }else{
                return arg2;
            }
        }
    }

    public static <T> boolean comparate(T val1, T val2, boolean isAsc){
        Assert.notNull(val1,"first param not null");
        Assert.notNull(val2,"second param not null");
        int compare = ComparatorUtils.NATURAL_COMPARATOR.compare(val1, val2);
        if (isAsc){
            if (compare <= 0){
                return true;
            }
        }else{
            if (compare >= 0){
                return true;
            }
        }
        return false;
    }
    /**
     * @desc collectionUtils4 工具类
     */

    public static <T> List<T> sortList(List<T> tList, boolean isAsc){
        Assert.notNull(tList,"sort list not null");
        Assert.noNullElements(tList.toArray(),"every element not null");
//        CollectionUtils.emptyIfNull(tList)
        if (CollectionUtils.isNotEmpty(tList)) {
            if (isAsc){
                Collections.sort(tList,ComparatorUtils.NATURAL_COMPARATOR);
            }else {
                Collections.sort(tList,ComparatorUtils.NATURAL_COMPARATOR.reversed());
            }

        }
        return tList;
    }

}
