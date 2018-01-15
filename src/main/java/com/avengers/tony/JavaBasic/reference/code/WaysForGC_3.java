package com.avengers.tony.JavaBasic.reference.code;

/**
 * Created by yucgu on 2018/1/10.
 */
public class WaysForGC_3 {

    String name;

    public WaysForGC_3(String name) {
        this.name = name;
    }

    static void show() {
        //object t1 inside method becomes unreachable when show() removed
        WaysForGC_3 t1 = new WaysForGC_3("t1");
        display();
    }

    static void display() {
        //object t2 inside method becomes unreachable when display() removed
        WaysForGC_3 t2 = new WaysForGC_3("t2");
    }

    public static void main(String args[]) {
        show();
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name + " successfully garbage collected");
    }
}

//    t2 successfully garbage collected
//    t1 successfully garbage collected