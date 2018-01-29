package com.avengers.Stark.JavaBasic.collection.list.vector.code;

import java.util.Vector;

public class VectorUsage {

    public static void main(String[] args) {

        Vector vec = new Vector();

        vec.add("1");
        vec.add("2");
        vec.add("3");
        vec.add("4");
        vec.add("5");

        vec.set(0, null);
        vec.add(2, "300");        // [null, 2, 300, 3, 4, 5]

        vec.indexOf("300");       // 2
        vec.indexOf("100");       // -1
        vec.lastIndexOf("100");   // -1

        vec.firstElement();       // null
        vec.elementAt(2);         // 300
        //vec.elementAt(10);        // ArrayIndexOutOfBoundsException
        vec.lastElement();        // 5

        vec.size();               // 6
        vec.capacity();           // 10

        vec.subList(1, 4);        // [2, 300, 3]

        Vector retainVec = new Vector();
        retainVec.add("100");
        retainVec.add("300");

        vec.retainAll(retainVec); // true
        // vec:[300]


        vec.clear();
        //vec.removeAllElements();// =>same as clear()

        vec.isEmpty();            // true

    }

}
