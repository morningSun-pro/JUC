package com.yuxin.zhouyang;
/**
 * 1.验证volatile的可见性.
 *  1.1假如int number = 0; number变量之前没有添加volatile关键字修饰,没有可见性.
 *  1.2添加了volatile,可以解决可见性问题.
 */

import java.util.concurrent.TimeUnit;

class myData{

    volatile int number =0;

    public  void Addto60(){
        this.number = 60;
    }

}

public class VolatileDemo {

    public static void main(String[] args) {
        myData myData = new myData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());   //获取当前线程名字
            //暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myData.Addto60();
            System.out.println(Thread.currentThread().getName()+"\t update number value: "+myData.number);
        },"AAA").start();

        while (myData.number == 0 ){
            //main线程一直在这里循环,直到number不等于零
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }

}
