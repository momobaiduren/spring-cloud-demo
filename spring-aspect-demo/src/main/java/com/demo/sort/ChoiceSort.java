package com.demo.sort;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhanglong
 * @Date 2019-09-13  18:15
 * @Version V1.0
 * 选择排序
 */
public class ChoiceSort {


    /**
     * @desc
     * 选择排序 - 首先初始化最小元素索引值为首元素，依次遍历待排序数列，
     * 若遇到小于该最小索引位置处的元素则刷新最小索引为该较小元素的位置，
     * 直至遇到尾元素，结束一次遍历，并将最小索引处元素与首元素交换；
     * 然后，初始化最小索引值为第二个待排序数列元素位置，
     * 同样的操作，可得到数列第二个元素即为次小元素；以此类推。
     *
     */
    public static <T> List<T> choiceSort2(boolean isAsc, T... tList) {
        Assert.notNull(tList, "sort list not null");
        Assert.noNullElements(tList, "every element not null");
        for (int i = 0; i < tList.length; i++) {
            int index = i;
            T temp = tList[i];
            for (int j = i + 1; j < tList.length; j++) {
                if (!ComparatorUtil.comparate(tList[i], tList[j], isAsc)) {
                    index = j;
                    temp = tList[index];
                }
            }
            if (index != i) {
                tList[index] = tList[i];
                tList[i] = temp;
            }

        }
        return Arrays.asList(tList);
    }

    public static void main(String[] args) {
        System.out.println(choiceSort2(false, 3, 1, 2));
    }
}
