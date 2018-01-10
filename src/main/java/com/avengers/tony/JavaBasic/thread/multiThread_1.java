package com.avengers.tony.JavaBasic.thread;

import java.util.concurrent.Semaphore;

public class multiThread_1 extends Thread {

    private Semaphore semaphore;
    private String threadName;
    private String series;

    private multiThread_1(Semaphore semaphore, String threadName, String series) {
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

    public static void main(String[] args) {
        String series = "010203040506";
        Semaphore sem = new Semaphore(1);
        multiThread_1 t1 = new multiThread_1(sem, "1",series);
        multiThread_1 t2 = new multiThread_1(sem, "2",series);
        multiThread_1 t3 = new multiThread_1(sem, "3",series);
        t1.start();
        t2.start();
        t3.start();
    }

}
