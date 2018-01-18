package com.avengers.tony.JavaBasic.comparator.code;

/**
 * Created by yucgu on 2018/1/9.
 */
public class AvengerNext extends Avenger implements Comparable<AvengerNext> {

    AvengerNext() {
        super();
    }

    public AvengerNext(String name, int age) {
        super(name, age);
    }

    public int compareTo(AvengerNext avenger) {
        return Integer.compare(super.getAge(), avenger.getAge());
    }
}
