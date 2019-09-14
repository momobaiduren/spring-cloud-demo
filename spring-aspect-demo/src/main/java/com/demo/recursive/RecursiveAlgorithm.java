package com.demo.recursive;

/**
 * @Author zhanglong
 * @Date 2019-09-13  18:26
 * @Version V1.
 * 递归算法
 */
public class RecursiveAlgorithm {

    private static Integer target = 5;

    public static Integer recursive(Integer source){
        System.out.println(source);
        source--;
        if (source == target){
            return source;
        }else {
            recursive(source);
        }
        return null;
    }

    public static void main(String[] args) {
        recursive(10);
    }
}
