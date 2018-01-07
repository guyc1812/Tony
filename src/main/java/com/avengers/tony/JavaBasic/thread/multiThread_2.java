package com.avengers.tony.JavaBasic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class multiThread_2 {

    private Runnable newRun(int NO, Semaphore semp){
        return () -> {
            try {
                semp.acquire();
                System.out.println(Thread.currentThread().getName()+" => Accessing: " + NO);
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semp.release();
            }
        };
    }

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        // only five threads at the same time
        final Semaphore semp = new Semaphore(3);

        // Simulate 20 clients access
        for (int index = 0; index < 20; index++) {
            exec.execute(new multiThread_2().newRun(index,semp));
        }

        exec.shutdown();
    }

}
