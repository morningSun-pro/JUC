package com.yuxin.zhouyang.Volatile;

/**
 * 单线程最经典单例模式
 */
public class SingletonDemo {
   /**
    * 禁止指令重排,指令重排只会保证单线程语义执行的一致性.当一条线程访问instance不为null时,由于instance实例尚未完成初始化.也就造成线程安全问题.
    * instance = new SingletonDemo();可以分为一下三步(伪代码)
    * 1.memory = allocate(); 分配内存空间
    * 2.instance(memory) 初始化对象
    * 3.instance = memory 设置instance指向刚分配的内存地址
    * 由于2.3部不存在数据依赖关系.，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变，因此
    * 这种重排优化时允许的，如果3步骤提前于步骤2，但是instance还没有初始化完成,出现线程安全问题.
    */
    private static volatile SingletonDemo instance =null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }

    //DCL (DOUBLE CHECK LOCK双端检锁机制)
    public static SingletonDemo getInstance(){
        if (instance ==null){
            synchronized (SingletonDemo.class){  //同步代码块
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
