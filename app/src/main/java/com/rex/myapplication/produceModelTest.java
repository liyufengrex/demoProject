package com.rex.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 生产者消费者模式
 * @Author: 李宇峰 on 2018-08-13 上午10:27.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class produceModelTest {

    public class Product {

    }

    public class Storage {

        List<Product> data = new ArrayList<>();
        private int index = 0;
        private int MAX = 20;

        public synchronized void produce(Product product) {
            while (index >= MAX) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            index++;
            data.add(product);
            notifyAll();
        }

        public synchronized Product sale() {
            while (index <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = data.get(index);
            index--;
            notifyAll();
            return product;
        }
    }

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //submit 提交需要返回值的任务
        pool.submit(new Callable<Object>() {
            @Override
            public Object call() {
                return "dfsfsdfds";
            }
        });

    }

}
