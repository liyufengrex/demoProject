package com.rex.myapplication;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2019-02-18 2:43 PM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class TestThread {

    public static class ThreadA extends Thread {

        private ThreadB b;

        public ThreadA(ThreadB b) {
            super();
            this.b = b;
        }

        @Override
        public void run() {
            try {
                synchronized (b) {
                    System.out.println(
                            "begin A ThreadName=" + Thread.currentThread().getName() + "  " + System
                                    .currentTimeMillis());
                    Thread.sleep(5000);
                    System.out.println(
                            "  end A ThreadName=" + Thread.currentThread().getName() + "  " + System
                                    .currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadB extends Thread {

        @Override
        synchronized public void run() {
            try {
                System.out.println(
                        "begin B ThreadName=" + Thread.currentThread().getName() + "  " + System
                                .currentTimeMillis());
                Thread.sleep(5000);
                System.out.println(
                        "  end B ThreadName=" + Thread.currentThread().getName() + "  " + System
                                .currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            b.start();
            b.join(2000);
            System.out.println("我要比B后执行哦！");//1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
