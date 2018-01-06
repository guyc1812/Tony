package com.avengers.core.ZZZ.string;

/**
 * Created by yucgu on 2017/11/29.
 */
public class StringDemo {

    public static void main(String[] args){

        String a = "a";
        String ac = "a";
        System.out.println( a == ac );

        String c = new String("c");
        String cc = "c";
        String ccc = "c";

        System.out.println( c == cc );
        System.out.println( ccc == cc );
        System.out.println( ccc == c );

        String ci = c.intern();

        System.out.println( c == cc );
        System.out.println( ci == cc );
        System.out.println( c == ci );

    }
}
