package com.avengers.tony.JavaBasic.lock.code;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo1 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Map<String, String> map = new HashMap<>();

        StampedLock lock = new StampedLock();

        Runnable writeTask = () -> {
            long stamp = lock.writeLock();
            try {
                map.put("foo", "bar");
                System.out.println(Thread.currentThread().getName() + " " + " stamp: " + stamp + " writing");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlockWrite(stamp);
            }
        };

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + " " + " stamp: " + stamp + " reading: " + map.get("foo"));
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(writeTask);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdown();

    }

}
