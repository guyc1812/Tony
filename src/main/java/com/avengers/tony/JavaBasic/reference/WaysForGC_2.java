package com.avengers.tony.JavaBasic.reference;

/**
 * Created by yucgu on 2018/1/10.
 */
public class WaysForGC_2 {

    String name;

    public WaysForGC_2(String name) {
        this.name = name;
    }

    public static void main(String args[]) {

        WaysForGC_2 t1 = new WaysForGC_2("t1");
        WaysForGC_2 t2 = new WaysForGC_2("t2");

        //t1 now referred to t2
        t1 = t2;

        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name + " successfully garbage collected");
    }

}

//  t1 successfully garbage collected