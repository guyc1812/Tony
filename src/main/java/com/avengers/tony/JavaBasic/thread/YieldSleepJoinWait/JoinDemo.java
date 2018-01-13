package com.avengers.tony.JavaBasic.thread.YieldSleepJoinWait;

import com.avengers.tony.JavaBasic.time.Timer;

public class JoinDemo {

    static class JoinRunnable implements Runnable {
        public void run() {
            try {
                Timer.start();
                System.out.println("[JoinThread]      START !");
                Thread.sleep(5000);
                System.out.print("[JoinThread]      FINISH --> ");
                Timer.end();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class JoinThread_Sync extends Thread {

        Thread thread;

        public JoinThread_Sync(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            synchronized (this.thread) {
                Timer.start();
                System.out.println("[JoinThread_Sync] LOCK AND START !");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.print("[JoinThread_Sync] UNLOCK AND FINISH --> ");
                Timer.end();
            }
        }
    }

    public static void main(String[] args){

        System.out.println("\n=====================================================\n");

        // test join method without synchronized
        try {
            System.out.println("["+Thread.currentThread().getName()+ "]            START !");
            Timer.start();
            Thread t = new Thread(new JoinRunnable());
            t.start();
            t.join(7000);
            System.out.print("["+Thread.currentThread().getName()+ "]            DONE --> ");
            Timer.end();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n=====================================================\n");


        // test join method with synchronized
        try {
            System.out.println("["+Thread.currentThread().getName()+ "]            START !");
            Timer.start();
            Thread t = new Thread(new JoinRunnable());
            Thread t_sync = new JoinThread_Sync(t);
            t_sync.start();
            t.start();
            t.join(4000);
            System.out.print("["+Thread.currentThread().getName()+ "]            DONE --> ");
            Timer.end();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
