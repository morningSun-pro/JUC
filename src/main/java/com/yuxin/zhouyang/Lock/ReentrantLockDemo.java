package com.yuxin.zhouyang.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间顺序调用,实现A->B->C
 * 三个线程启动,要求如下
 * AA打印5次BB打印10次CC打印15次
 * 接着
 * AA打印5次BB打印10次CC打印15次
 * 来十轮
 */
class ShareResource{
    private int number =1;   //标志位
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void print5(){
        lock.lock();
        try{
            while(number != 1){     //判断
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {       //干活
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 2;   //修改标志位
            condition2.signal();     //通知
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            while(number != 2){     //判断
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {       //干活
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 3;   //修改标志位
            condition3.signal();     //通知
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            while(number != 3){     //判断
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {       //干活
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;   //修改标志位
            condition1.signal();     //通知
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        for (int i = 1; i <= 10 ; i++) {
            new Thread(() -> {
                shareResource.print10();
            }, "B").start();


            new Thread(() -> {
                shareResource.print15();
            }, "C").start();

            new Thread(() -> {
                shareResource.print5();
            }, "A").start();
        }
    }
}
