package com.yuxin.zhouyang;

/**
 * 单线程最经典单例模式
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance =null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }

    //DCL (DOUBLE CHECK LOCK双端检锁机制)
    public static SingletonDemo getInstance(){
        if (instance ==null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
