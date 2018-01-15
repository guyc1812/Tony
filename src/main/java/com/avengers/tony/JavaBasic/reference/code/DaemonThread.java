package com.avengers.tony.JavaBasic.reference.code;

/**
 * Created by yucgu on 2018/1/10.
 */
public class DaemonThread extends Thread {

    public void run() {
        // Checking whether the thread is Daemon or not
        if (Thread.currentThread().isDaemon()) {
            System.out.println("This is Daemon thread");
        } else {
            System.out.println("This is User thread");
        }
    }

    public static void main(String[] args) {

        DaemonThread t1 = new DaemonThread();
        DaemonThread t2 = new DaemonThread();
        DaemonThread t3 = new DaemonThread();

        // Setting user thread t1 to Daemon
        t1.setDaemon(true);

        // starting all the threads
        t1.start();
        t2.start();
        t3.start();

        // t3.setDaemon(true);
        // Exception in thread "main" java.lang.IllegalThreadStateException
        // We cannot call the setDaemon() method after starting the thread.

    }
}