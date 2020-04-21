package com.yuxin.zhouyang;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证volatile不保存原子性
 * 1.1 原子性指的是什么意思?
 *      不可分割,完整性,也即某个线程正在做某个具体业务时,中间不可以被加塞或者被分割,需要整体完整,
 *      要么同时成功,要么同时失败.
 *
 */

class Data{
    volatile int number =0;
    public int addPlusPlus() {
        return number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();  //原子性
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}


public class VolatileDemo2 {
    public static void main(String[] args) {
        Data Data = new Data();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++){
                    Data.addPlusPlus();
                    Data.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        //很重要,默认后台有连个线程,main,和GC
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t type int finally number value: "+Data.number);
        System.out.println(Thread.currentThread().getName()+"\t type Atomic finally number value: "+Data.atomicInteger);
    }
}
