package com.avengers.core.demo.JavaBasic.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yucgu on 2017/11/24.
 */
public class lock_rw {


    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private synchronized void getSync(Thread thread) {
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

    private void getRead(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }


    public static void main(String[] args)  {

        final lock_rw test = new lock_rw();

        Thread t1 = new Thread(() -> test.getSync(Thread.currentThread()));
        Thread t2 = new Thread(() -> test.getSync(Thread.currentThread()));
        Thread t3 = new Thread(() -> test.getRead(Thread.currentThread()));
        Thread t4 = new Thread(() -> test.getRead(Thread.currentThread()));

        t1.start();
        t2.start();
        try{Thread.sleep(3000);} catch ( Exception e){ e.printStackTrace(); }
        t3.start();
        t4.start(); //t3和t4在同时进行读操作。

    }


}

