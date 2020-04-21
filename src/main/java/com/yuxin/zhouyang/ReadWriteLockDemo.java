package com.yuxin.zhouyang;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
class myCache{
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    Map map = new HashMap();
    public void put(String key, Object value){
        rwLock.writeLock().lock();
        try {
            System.out.println("线程 "+Thread.currentThread().getName()+"\t 正在写入"+key);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println("线程 "+Thread.currentThread().getName()+"\t 写入完成"+map.get(key));
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println("线程 "+Thread.currentThread().getName()+"\t 正在读取");
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println("线程 "+Thread.currentThread().getName()+"\t 读取完成"+result);
        }finally {
            rwLock.readLock().unlock();
        }
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        myCache myCache = new myCache();
        for (int i = 1; i <= 50; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 50; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI+"");
            }, String.valueOf(i)).start();
        }
    }
}
