package com.avengers.tony.JavaBasic.reference;

/**
 * Created by yucgu on 2018/1/10.
 */
public class WaysForGC_4 {

    String name;

    public WaysForGC_4(String name) {
        this.name = name;
    }

    public static void main(String args[]) {
        new WaysForGC_4("t1");
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name + " successfully garbage collected");
    }
}
