package com.avengers.tony.JavaBasic.thread.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        // only five threads at the same time
        final Semaphore semp = new Semaphore(5);

        // Simulate 20 clients access
        for (int index = 0; index < 20; index++) {
            exec.execute(new SemaphoreDemo().newRun(index, semp));
        }

        exec.shutdown();
    }

    private Runnable newRun(int NO, Semaphore semp) {
        return () -> {
            try {
                semp.acquire();
                System.out.println(Thread.currentThread().getName() + " => Accessing: " + NO);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semp.release();
            }
        };
    }

}
