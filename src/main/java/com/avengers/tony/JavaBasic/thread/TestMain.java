package com.avengers.tony.JavaBasic.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by yucgu on 2018/1/10.
 */
public class TestMain {

    public static void main(String args[]) {
        String series = "010203040506";
        Semaphore sem = new Semaphore(1);
        MultiThreads_Runnable R1 = new MultiThreads_Runnable(sem, "1",series);
        MultiThreads_Runnable R2 = new MultiThreads_Runnable(sem, "2",series);
        MultiThreads_Runnable R3 = new MultiThreads_Runnable(sem, "3",series);
        Thread t1 = new Thread(R1);
        Thread t2 = new Thread(R2);
        Thread t3 = new Thread(R3);
        t1.start();
        t2.start();
        t3.start();
        t3.run();
    }

}
