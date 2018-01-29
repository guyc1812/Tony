package com.avengers.Stark.JavaBasic.string.code;

public class StringUsage {

    public static void main(String[] args) {

        String a = "a";
        String ac = "a";
        System.out.println(a == ac);

        String c = new String("c");
        String cc = "c";
        String ccc = "c";

        System.out.println(c == cc);
        System.out.println(ccc == cc);
        System.out.println(ccc == c);

        String ci = c.intern();

        System.out.println(c == cc);
        System.out.println(ci == cc);
        System.out.println(c == ci);

    }
}
