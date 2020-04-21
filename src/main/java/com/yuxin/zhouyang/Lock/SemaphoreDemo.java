package com.yuxin.zhouyang.Lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 可以代替Synchronize和Lock
 * 1. 信号量主要用于两个目的，一个是用于多个共享资源的互斥作用，另一个用于并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                  //  semaphore.acquire();  //获得锁
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    //停车3秒钟
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();    //释放锁
                }
            }, String.valueOf(i)).start();
        }

    }
}
