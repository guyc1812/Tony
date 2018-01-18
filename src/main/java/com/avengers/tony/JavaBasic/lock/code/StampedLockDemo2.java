package com.avengers.tony.JavaBasic.lock.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo2 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();  // non blocking
            // do some read
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                Thread.sleep(300);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                Thread.sleep(300);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                Thread.sleep(300);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                Thread.sleep(300);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                if (!lock.validate(stamp)) {        // if a write occurred, try again with a read lock
                    long readStamp = lock.readLock();
                    try {
                        // do some read
                    } finally {
                        lock.unlock(readStamp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        executor.shutdown();
    }

}
