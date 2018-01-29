package com.avengers.Stark.JavaBasic.lock.code;


public class SynchronizedDemo {

    public static synchronized void syncStatic() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void syncNone() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void syncMethod1() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void syncMethod2() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void syncBlock() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + "\t count is " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
