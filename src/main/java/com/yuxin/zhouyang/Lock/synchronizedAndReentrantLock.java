package com.yuxin.zhouyang.Lock;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁:
 * sysnchronized 和ReentrantLock都是可重入锁
 * 指的时同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁
 * 的时候，在进入内层方法会自动获取锁，也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 */
class RL{

    public synchronized void get() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t get() come in here");
        TimeUnit.SECONDS.sleep(3);
        set();
    }

    public synchronized void set(){
        System.out.println(Thread.currentThread().getName()+"\t ************set() come in here");
    }
}
public class synchronizedAndReentrantLock {
    public static void main(String[] args){
        RL rl = new RL();

        new Thread(() -> {
            try {
                rl.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                rl.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();


    }
}
