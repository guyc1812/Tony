package com.avengers.tony.JavaBasic.lock.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {


    public static void main(String[] args) {
        ReentrantLock rel = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Runnable w1 = new worker(rel, "worker-1");
        Runnable w2 = new worker(rel, "worker-2");
        Runnable w3 = new worker(rel, "worker-3");
        Runnable w4 = new worker(rel, "worker-4");
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }

    static class worker implements Runnable {
        String name;
        ReentrantLock re;

        public worker(ReentrantLock re, String name) {
            this.re = re;
            this.name = name;
        }

        public void run()

        {
            boolean done = false;
            while (!done) {

                boolean ans = re.tryLock();

                // Returns True if lock is free
                if (ans) {

                    try {

                        System.out.println(this.name + " is in");
                        System.out.println(this.name + " is working on job-1");
                        System.out.println(this.name + " is holding lock count-" + re.getHoldCount());

                        re.lock();

                        try {
                            System.out.println(this.name + " is working on job-2");
                            System.out.println(this.name + " is holding lock count-" + re.getHoldCount());
                            Thread.sleep(1500);
                            System.out.println(this.name + " is done job-2");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            re.unlock();
                        }
                        System.out.println(this.name + " is holding lock count-" + re.getHoldCount());
                        System.out.println(this.name + " is done job-1");

                        done = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        re.unlock();
                        System.out.println(this.name + " is holding lock count-" + re.getHoldCount());
                        System.out.println(this.name + " is out");
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
