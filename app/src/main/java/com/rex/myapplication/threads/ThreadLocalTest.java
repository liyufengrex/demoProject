package com.rex.myapplication.threads;

import android.os.Looper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 描述
 * @Author: liyufeng
 * @CreateDate: 2020-06-22 14:25
 */

public class ThreadLocalTest {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>()
            {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

}
