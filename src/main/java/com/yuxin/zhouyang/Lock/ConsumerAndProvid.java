package com.yuxin.zhouyang.Lock;

import jdk.nashorn.internal.objects.annotations.Where;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 消费者,提供者
 *
 * 高内聚,内耦合,线程控制资源类
 *  判断,干活,修改标志位,通知
 *  防止虚假唤醒
 */
class shareData{
    ReentrantLock reentrantLock = new ReentrantLock();
    private volatile int number = 0;
    Condition condition = reentrantLock.newCondition();

    public void increment() throws InterruptedException {
        reentrantLock.lock();
        try {
            while(number != 0){
                condition.await();
            }
            System.out.println(number++);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void decrease() throws InterruptedException {
        reentrantLock.lock();
        try {
            while(number == 0){
                condition.await();
            }
            System.out.println(number--);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
public class ConsumerAndProvid {
    public static void main(String[] args) {
        //线程操作资源类
        shareData shareData = new shareData();
        for (int i = 1; i <= 15 ; i++) {
            new Thread(() -> {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "AA").start();
        }

        for (int i = 1; i <= 15 ; i++) {
            new Thread(() -> {
                try {
                    shareData.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "BB").start();
        }

    }
}
