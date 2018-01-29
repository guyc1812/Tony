package com.avengers.Stark.JavaBasic;

import java.util.concurrent.*;

public class Test {

    ConcurrentHashMap<Integer,Integer> cm = new ConcurrentHashMap<>();

    public synchronized void put(){
//        System.out.println(Thread.currentThread().getName()+" => start");
        try {
            if(!cm.containsKey(1)){
                Thread.sleep(500);
                cm.put(1,1);
                System.out.println(Thread.currentThread().getName()+" => put 1");
            }else if(!cm.containsKey(2)){
                Thread.sleep(500);
                cm.put(2,2);
                System.out.println(Thread.currentThread().getName()+" => put 2");
            }else if(!cm.containsKey(3)){
                Thread.sleep(500);
                cm.put(3,3);
                System.out.println(Thread.currentThread().getName()+" => put 3");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args){


        Test test = new Test();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for(int i = 0; i<100;i++) pool.submit(() -> test.put());
        pool.shutdown();

    }

























}
