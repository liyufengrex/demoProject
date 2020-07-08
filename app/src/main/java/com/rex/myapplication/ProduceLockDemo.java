package com.rex.myapplication;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-10-23 下午2:49.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class ProduceLockDemo {

    static class Product {

        public Product(String name) {
            this.name = name;
        }

        String name;

        public String getName() {
            return this.name;
        }
    }

    Queue<Product> data = new ArrayDeque<>();
    private int index = 0;
    private int MAX = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition notEmptyCondition = lock.newCondition();
    Condition notFullCondition = lock.newCondition();

    /**
     * 销售
     *
     * @author liyufeng[13756017116]@2018-10-23 下午2:50
     */
    public Product sale() {
        Product p = null;
        lock.lock();
        try {
            while (index <= 0) {
                System.out.println("还没有生产好，等待....");
                notEmptyCondition.await();
            }
            p = data.poll();
            index--;
            notFullCondition.signal();
        } catch (InterruptedException e) {

        } finally {
            System.out.println("unlock");
            lock.unlock();
        }
        System.out.println("消费 ：" + p.getName());
        return p;
    }


    /**
     * 生产
     *
     * @author liyufeng[13756017116]@2018-10-23 下午2:50
     */
    public void produce(Product product) {
        lock.lock();
        try {
            while (index >= MAX) {
                System.out.println("仓库已达上限，等待....");
                notFullCondition.await();
            }
            System.out.println("生产 ：" + product.getName());
            data.add(product);
            index++;
            notEmptyCondition.signal();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public static class SaleRunnable implements Runnable {

        ProduceLockDemo demo;

        public SaleRunnable(ProduceLockDemo demo) {
            this.demo = demo;
        }

        @Override
        public void run() {
            demo.sale();
        }
    }

    static class ProduceRunnable implements Runnable {

        ProduceLockDemo demo;
        String name;

        public ProduceRunnable(ProduceLockDemo demo,String name) {
            this.demo = demo;
            this.name = name;
        }

        @Override
        public void run() {
            demo.produce(new Product(name));
        }
    }

    public static void main(String[] args) {
        ProduceLockDemo demo = new ProduceLockDemo();
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new SaleRunnable(demo));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new ProduceRunnable(demo,"1"));
        pool.execute(new ProduceRunnable(demo,"2"));
        pool.execute(new ProduceRunnable(demo,"3"));
        pool.execute(new ProduceRunnable(demo,"4"));
        pool.execute(new ProduceRunnable(demo,"5"));
        pool.execute(new ProduceRunnable(demo,"6"));
        pool.execute(new ProduceRunnable(demo,"7"));
        pool.execute(new ProduceRunnable(demo,"8"));
        pool.execute(new ProduceRunnable(demo,"9"));
        pool.execute(new ProduceRunnable(demo,"10"));
        pool.execute(new ProduceRunnable(demo,"11"));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new SaleRunnable(demo));
        pool.execute(new ProduceRunnable(demo,"12"));
        pool.execute(new ProduceRunnable(demo,"13"));

    }
}
