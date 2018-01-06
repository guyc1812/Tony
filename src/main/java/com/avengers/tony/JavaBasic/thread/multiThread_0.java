package com.avengers.core.demo.JavaBasic.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by yucgu on 2017/11/24.
 */
public class multiThread_0 implements Runnable {

    private Semaphore semaphore;
    private String threadName;
    private String series;

    private multiThread_0(Semaphore semaphore, String threadName, String series) {
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
                    System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur==0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
                case "2":
                    System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur%2==0&&cur!=0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
                case "3":
                    System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
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
        multiThread_0 R1 = new multiThread_0(sem, "1",series);
        multiThread_0 R2 = new multiThread_0(sem, "2",series);
        multiThread_0 R3 = new multiThread_0(sem, "3",series);
        Thread t1 = new Thread(R1);
        Thread t2 = new Thread(R2);
        Thread t3 = new Thread(R3);
        t1.start();
        t2.start();
        t3.start();
    }


}
