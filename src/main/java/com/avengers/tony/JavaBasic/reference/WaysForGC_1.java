package com.avengers.tony.JavaBasic.reference;

/**
 * Created by yucgu on 2018/1/10.
 */
public class WaysForGC_1 {

    // to store object name
    String name;

    public WaysForGC_1(String name) {
        this.name = name;
    }

    public static void main(String args[]) {

        WaysForGC_1 t1 = new WaysForGC_1("t1");

        t1 = null;

        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        // will print name of object
        System.out.println(this.name + " successfully garbage collected");
    }

}
