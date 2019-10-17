package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  4:34 下午
 */
public class SingletionMain {
    /**
     * @Author zhanglong
     * @Date 2019/9/30
     */

    public static void main(String[] args) {
        //饿汉
//        runMoreThread(100000,HungrySingleton.instance());
        //懒汉
        runMoreThread(100000, LazySingleton.instance());
    }

    private static void runMoreThread(int threadNum, Singleton singleton) {
        for (int i = 0; i < threadNum; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("---->" + finalI);
                    int instance = singleton.i;
                    if (instance == 1) {
                        System.out.println(instance);
                    }
                }
            });
        }
    }
}
