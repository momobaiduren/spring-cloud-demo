package com.demo.thread;

import java.util.concurrent.Exchanger;

/**
 * @author ZhangLong on 2019/11/3  2:39 下午
 * @version V1.0 线程交换资源 在特定条件交换资源  必须成对出现交换线程共用一个exchanger
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            String s1 = "xiancheng1";
            System.out.println("线程1："+s1);
            try {
                //处于等待状态 直到线程2发起交换操作
                String exchange = exchanger.exchange(s1);
                System.out.println("线程1交换后："+exchange);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }).start();
        new Thread(()->{
            String s2 = "xiancheng2";
            System.out.println("线程2："+s2);
            try {
                //处于等待状态 直到线程1发起交换操作
                String exchange = exchanger.exchange(s2);
                System.out.println("线程2交换后："+exchange);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }).start();
    }
}
