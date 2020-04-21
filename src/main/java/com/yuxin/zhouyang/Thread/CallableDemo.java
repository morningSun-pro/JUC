package com.yuxin.zhouyang.Thread;

import java.util.concurrent.*;

/**
 * 多线程实现方式
 *     1.继承Thread类
 *     2.实现Runnable接口
 *     3.实现Callable接口
 */

class myThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("*****coom in here !");
        return 1024;
    }
}


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * FutureTask实现了Runnable接口,同时构造方法中需要传入一个实现了Callable接口的类;
         * 这就是Java多态方法的使用
         */
        FutureTask futureTask = new FutureTask<>(new myThread());
        new Thread(futureTask,"T").start();
        System.out.println(futureTask.get());
    }
}
