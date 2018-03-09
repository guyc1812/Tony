package com.avengers.Stark.JavaBasic.thread.code;


public class Thread_join_demo {

    private static Thread t0 = new Thread(() -> {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " =>0");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    private static Thread t1 = new Thread(() -> {
        try {
            t0.join();
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " =>1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    private static Thread t2 = new Thread(() -> {
        try {
            t1.join();
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " =>2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public static void main(String args[]) {
        t2.start();
        t1.start();
        t0.start();
        // Thread-0 =>0
        // Thread-1 =>1
        // Thread-2 =>2
    }

}
