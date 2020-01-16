package com.avengers.Stark.JavaBasic.lock.code.MCS;

import java.util.concurrent.locks.Lock;

class MCSTask implements Runnable {

    private Lock lock;

    private MCSTask(final Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1);
            System.out.println(String.format("Thread %s Completed", Thread.currentThread().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Lock MCSLock = new MCSLock();
        for (int i = 0; i < 10; i++) {
            MCSTask MCSTask = new MCSTask(MCSLock);
            Thread MCSThread = new Thread(MCSTask);
            MCSThread.start();
        }
    }
}