package com.rex.myapplication.threads;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-06-04 15:28
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {

    private ReentrantLock lock = new ReentrantLock();
    private Condition two = lock.newCondition();
    private Condition three = lock.newCondition();

    private boolean isOneFinished = false;
    private boolean isTwoFinished = false;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        lock.lock();
        printFirst.run();
        isOneFinished = true;
        two.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        System.out.println("second lock");
        lock.lock();
        if(!isOneFinished){
            two.await();
        }
        printSecond.run();
        isTwoFinished = true;
        three.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        System.out.println("third lock");
        lock.lock();
        if(!isTwoFinished){
            three.await();
        }
        printThird.run();
        lock.unlock();
    }

    public static void main(String[] args){
        Foo foo = new Foo();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.first(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("one");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.second(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("two");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.third(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("three");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
