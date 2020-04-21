package com.yuxin.zhouyang.Lock;

import com.yuxin.zhouyang.Enum.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 火箭发射倒计时）
 * 1. 它允许一个或多个线程一直等待，知道其他线程的操作执行完后再执行。例如，应用程序的主线程希望在负责
 * 启动框架服务的线程已经启动所有的框架服务之后再执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "灭亡");
                countDownLatch.countDown();  //线程计数减1
            }, CountryEnum.forEach_CountryEnum(i).getCountryName()).start();
        }
        countDownLatch.await(); //主线程等待上面线程结束才能执行
        System.out.println("秦一统华夏");
    }
}
