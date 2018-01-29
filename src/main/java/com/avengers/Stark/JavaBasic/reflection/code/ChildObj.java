package com.avengers.Stark.JavaBasic.reflection.code;

public class ChildObj extends RootObj {

    private String childfield1;
    private String childfield2;

    public ChildObj() {
        this.childfield1 = "This is child field1";
        this.childfield2 = "This is child field2";
    }

    private ChildObj(String text) {    //note this is a private constructor
        System.out.println("This is child constructor with para");
    }

    public void childMethod1() {
        System.out.println("This is child method1(public)");
    }

    public void childMethod2(int n) {
        System.out.println("This is child method2(public) with int para: " + n);
    }

    private void childMethod3() {       //note this is a private method
        System.out.println("This is child method3(private)");
    }
}
