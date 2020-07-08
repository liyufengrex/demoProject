package com.rex.myapplication.threads;

import com.rex.contentrex.ModuleRex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-08-14 上午11:47.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class ThreadTest {

    public static void main(String[] args) {
        //        ExecutorService pool = Executors.newCachedThreadPool();
        //        Lock lock = new ReentrantLock();
        //        pool.execute(new User(lock));
        //        pool.shutdown();

        //        test();

        Set<String> set = new HashSet<>();
        String str = new String("rex");
        set.add(str);
        set.add(str);

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        ModuleRex.test();

    }

    private void maoPaoSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length - 1; j++) {

                if (arr[j] >= arr[j + 1]) {
                    exchange(arr, j, j + 1);
                }

            }

        }
    }

    private void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int base = arr[end];
        int n = start;
        for (int i = start; i < end; i++) {

            if (arr[i] <= base) {
                exchange(arr, i, n);
                n++;
            }

        }
        exchange(arr, end, n);
        quickSort(arr, start, n);
        quickSort(arr, n + 1, end);
    }

    private void exchange(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }


    private static void test() {
        HashMap<String, String> data = new HashMap<>();
        Iterator<Entry<String, String>> iterator = data.entrySet().iterator();
        while ((iterator.hasNext())) {
            System.out.print(iterator.next().getKey());
        }
    }

    public static class User implements Runnable {

        private Lock lock;

        public User(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            //进行操作
            lock.unlock();
        }
    }

    static class SingletonStaticInner {

        private SingletonStaticInner() {
        }

        private static class SingleInner {

            private static SingletonStaticInner singletonStaticInner = new SingletonStaticInner();
        }

        public static SingletonStaticInner getSingletonStaticInner() {
            return SingleInner.singletonStaticInner;
        }
    }


}
