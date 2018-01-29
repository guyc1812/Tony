package com.avengers.Stark.JavaBasic.lock.code;

/**
 * Created by yucgu on 2018/1/18.
 */
public class SynchronizedDriven {

    public static void main(String[] args) {

        SynchronizedDemo sd = new SynchronizedDemo();

        Thread t0 = new Thread(sd::syncMethod1);
        Thread t1 = new Thread(sd::syncMethod2);
        Thread t2 = new Thread(sd::syncBlock);
        Thread t3 = new Thread(sd::syncNone);
        Thread t4 = new Thread(SynchronizedDemo::syncStatic);
        Thread t5 = new Thread(SynchronizedDemo::syncStatic);

//        t0.start();
//        t1.start();
//        t2.start();
//        t3.start();
        t4.start();
        t5.start();

    }
}
