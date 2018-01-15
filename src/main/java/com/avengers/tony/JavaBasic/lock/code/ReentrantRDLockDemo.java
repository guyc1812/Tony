package com.avengers.tony.JavaBasic.lock.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantRDLockDemo {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static String message = "a";

    static class Read implements Runnable {

        public void run() {
            for (int i = 0; i <= 5; i++) {
                if (lock.isWriteLocked()) {
                    System.out.println("I'll take the lock from Write");
                }
                lock.readLock().lock();
                System.out.println("R " + Thread.currentThread().getName() + " ---> Message is " + message);
                lock.readLock().unlock();
            }
        }
    }

    static class Write implements Runnable {

        public void run() {
            for (int i = 0; i <= 5; i++) {
                try {
                    lock.writeLock().lock();
                    System.out.println("W " + Thread.currentThread().getName() + " ---> Message is " + message);
                    message = message.concat("a");
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.execute(new Read());
        pool.execute(new Read());
        pool.execute(new Write());
        pool.execute(new Write());
        pool.shutdown();
    }

}
