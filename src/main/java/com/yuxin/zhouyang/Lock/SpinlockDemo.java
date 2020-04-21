package com.yuxin.zhouyang.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinlockDemo {
    AtomicReference<Thread> reference = new AtomicReference<>();  //原子引用线程
    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in here");
        while (!reference.compareAndSet(null,thread)){}
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in here unlock");
        reference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        SpinlockDemo spinLock = new SpinlockDemo();

        new Thread(() -> {
            spinLock.lock();
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.unlock();
        }, "T1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLock.lock();
            spinLock.unlock();
        }, "T2").start();
    }
}
