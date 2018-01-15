package com.avengers.tony.JavaBasic.thread.code;

import com.avengers.tony.JavaBasic.time.Timer;

public class Thread_WaitSleep {

    private static Object obj = new Object();

    static class WaitThread extends Thread{

        public WaitThread(String name){
            super(name);
            System.out.println("Thread " + name + " is created");
        }

        public void run() {
            synchronized (obj) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        WaitThread t1 = new WaitThread("t1");
        WaitThread t2 = new WaitThread("t2");
        WaitThread t3 = new WaitThread("t3");

        try {
            Timer.start();
            Thread.sleep(3000);
            System.out.print(Thread.currentThread().getName()+" sleep for ");
            Timer.end();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();
        t2.start();
        t3.start();

        synchronized(obj) {
            System.out.println(Thread.currentThread().getName()+" notifyAll()");
            obj.notifyAll();
        }
    }
    
}
