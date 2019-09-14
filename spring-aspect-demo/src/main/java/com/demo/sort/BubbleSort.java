package com.demo.sort;


import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhanglong
 * @Date 2019-09-13  18:14
 * @Version V1.0
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * @desc
     *  冒泡排序 - 依次比较相邻两元素，若前一元素大于后一元素则交换之，直至最后一个元素即为最大；
     *  然后重新从首元素开始重复同样的操作，直至倒数第二个元素即为次大元素；
     *  依次类推。如同水中的气泡，依次将最大或最小元素气泡浮出水面。
     *
     */
    public static <T> List<T> bubbleSort( boolean isAsc,T ... tList){
        Assert.notNull(tList, "sort List could not null");
        Assert.noNullElements(tList, "every element not null");
        T temp;
        for (int i = 0; i < tList.length ; i++) {
            for (int j = i+1; j < tList.length; j++) {
                if (!ComparatorUtil.comparate(tList[i],tList[j],isAsc)){
                    temp = tList[i];
                    tList[i] = tList[j];
                    tList[j] = temp;
                }
            }
        }
        return Arrays.asList(tList);
    }
}
