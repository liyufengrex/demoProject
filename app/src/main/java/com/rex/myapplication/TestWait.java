package com.rex.myapplication;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-08-20 上午9:51.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class TestWait {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        CyclicBarrier barrier = new CyclicBarrier(5, new BarrierRunnable());
        new SubRunnable("A",barrier).start();
        new SubRunnable("B",barrier).start();
        new SubRunnable("C",barrier).start();
        new SubRunnable("D",barrier).start();
        new SubRunnable("E",barrier).start();

//        try {
//            System.out.println("result : "+ preserveOrderViaCountdownLatch());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public static class BarrierRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("rex");
        }
    }

    public static class SubRunnable extends Thread {

        private String name;
        private CyclicBarrier barrier;

        public SubRunnable(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("name: "+name);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public static long preserveOrderViaCountdownLatch() throws InterruptedException {
        int count = 5;
        long startMillis = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();// 只要计数器清零，等待的线程就可以开始执行，于是可以达到并发的效果
                }
            }, "countDownLatch-" + i);
            t.start();
            t.join();
        }
        countDownLatch.await();
        return System.currentTimeMillis() - startMillis;
    }


}
