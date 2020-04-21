package com.yuxin.zhouyang.Queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 是一个不存储元素的阻塞队列,生产一个消费一个.
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try
            {
                for (int i = 1; i <=3 ; i++) {
                    synchronousQueue.put(i);
                    System.out.println(Thread.currentThread().getName()+"\t put "+i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            try
            {
                for (int i = 1; i <= 3; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
