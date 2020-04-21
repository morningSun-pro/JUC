package com.yuxin.zhouyang.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * 模拟排队吃饭等候区
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<Integer>(3);
       // add , remove element
        /** arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(3);
       // arrayBlockingQueue.add(4);  //非法状态异常

        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        //System.out.println(arrayBlockingQueue.element()); //没有这样的元素异常
        arrayBlockingQueue.remove();//没有这样的元素异常*/

        //offer poll  peek
        /*arrayBlockingQueue.offer(1);
        arrayBlockingQueue.offer(2);
        arrayBlockingQueue.offer(3);
        System.out.println(arrayBlockingQueue.offer(4));  //队列满了就返回fasle
        arrayBlockingQueue.poll();
        arrayBlockingQueue.poll();
        arrayBlockingQueue.poll();
        System.out.println(arrayBlockingQueue.poll());         //没有就返回null
        System.out.println(arrayBlockingQueue.peek());          //没有就返回null*/

        //put   take
       /** arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(1);
        //arrayBlockingQueue.put(1);      //满了就死等
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        //arrayBlockingQueue.take();        //必须消费完才走*/
        //offer poll
        arrayBlockingQueue.offer(1,3,TimeUnit.SECONDS);
        arrayBlockingQueue.offer(2,3,TimeUnit.SECONDS);
        arrayBlockingQueue.offer(3,3,TimeUnit.SECONDS);
        //System.out.println(arrayBlockingQueue.offer(4, 3, TimeUnit.SECONDS));   //    过时不候
        arrayBlockingQueue.poll(3,TimeUnit.SECONDS);
        arrayBlockingQueue.poll(3,TimeUnit.SECONDS);
        arrayBlockingQueue.poll(3,TimeUnit.SECONDS);
        arrayBlockingQueue.poll(3,TimeUnit.SECONDS);

    }
}
