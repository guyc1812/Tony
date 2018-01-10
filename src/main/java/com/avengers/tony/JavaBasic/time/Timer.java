package com.avengers.tony.JavaBasic.time;

/**
 * Created by yucgu on 2018/1/4.
 */
public class Timer {

    private static long start;
    private static long end;

    public static void start(){
        start = System.currentTimeMillis();
    }

    public static void end(){
        end = System.currentTimeMillis();
        System.out.println("Timer : " + (end-start) + " ms");
    }


}
