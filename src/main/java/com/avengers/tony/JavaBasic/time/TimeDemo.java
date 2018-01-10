package com.avengers.tony.JavaBasic.time;

import java.util.Random;

/**
 * Created by yucgu on 2018/1/4.
 */
public class TimeDemo {

    private int randomGen(int bound){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(bound);
    }

    public static void main (String[] args){
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
        System.out.println(new TimeDemo().randomGen(100));
    }



}
