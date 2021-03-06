package com.yuxin.zhouyang.Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *可循环（Cyclic）使用的屏障。让一组线程到达一个屏障（也可叫同步点）时被阻塞，知道最后一个线程到达屏
 * 障时，屏障才会开门，所有被屏障拦截的线程才会继续干活，线程进入屏障通过CycliBarrier的await()方法
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //直到最后一个人到了才会执行这个线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,() ->{ System.out.println("人到齐了,会议可以开始"); });

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 进来了");
                    cyclicBarrier.await(); //人数不够的情况下持续等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}