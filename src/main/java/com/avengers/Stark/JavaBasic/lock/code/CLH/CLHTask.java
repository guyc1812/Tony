package com.avengers.Stark.JavaBasic.lock.code.CLH;

import java.util.concurrent.locks.Lock;

class CLHTask implements Runnable {

    private Lock lock;

    private CLHTask(final Lock lock) {
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
        final Lock clhLock = new CLHLock();
        for (int i = 0; i < 10; i++) {
            CLHTask clhTask = new CLHTask(clhLock);
            Thread clhThread = new Thread(clhTask);
            clhThread.start();
        }
    }
}