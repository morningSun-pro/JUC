package com.yuxin.zhouyang.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 演示ABA问题
 */
public class ABADemo {
    static AtomicReference atomicReference = new AtomicReference(100);
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,1);
    public static void main(String[] args) {
        System.out.println("===============以下是ABA问题的产生==================");
        new Thread(() -> {
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        }, "T1").start();

        new Thread(() -> {
            //暂停一秒钟T2线程保证上面的T1线程完成一次ABA操作.
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100,2020)+"\t"+atomicReference.get().toString());
        }, "T2").start();

        //暂停一会线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("========================以下是ABA问题的解决================================");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次版本号是: "+stamp);
            //暂停T3线程一秒钟保证T4线程获取的版本号和当前版本一样
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("第二次版本号是: "+ atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("第三次版本号是: "+ atomicStampedReference.getStamp());
        }, "T3").start();

        new Thread(() -> {
            int stamp =atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次版本号是: "+stamp);
            //暂停T4线程3秒钟保证T3可以完成一次ABA操作
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicStampedReference.compareAndSet(100,101,stamp,atomicStampedReference.getStamp()+1)+"\t 当前版本号是: "+atomicStampedReference.getStamp());
            System.out.println("当前最新值是: "+atomicStampedReference.getReference());
        }, "T4").start();
    }
}
