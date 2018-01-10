package com.avengers.tony.JavaBasic.thread;

import java.util.concurrent.Semaphore;

public class MultiThreads_Runnable implements Runnable {

    private Semaphore semaphore;
    private String threadName;
    private String series;

    public MultiThreads_Runnable(Semaphore semaphore, String threadName, String series) {
        this.semaphore = semaphore;
        this.threadName = threadName;
        this.series = series;
    }

    public void run() {
        try {
            semaphore.acquire();
            StringBuilder sb = new StringBuilder();
            switch (threadName) {
                case "1":
                    System.out.println(Thread.currentThread().getName() + " START !");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur==0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
                case "2":
                    System.out.println(Thread.currentThread().getName() + " START !");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur%2==0&&cur!=0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
                case "3":
                    System.out.println(Thread.currentThread().getName() + " START !");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur%2!=0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
            }
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
//        t3.run();
    }


}
