package com.avengers.core.demo.JavaBasic.reflection;

/*****
 * Created by apple on 2017/12/10.
 */
public class RootObj {

    private String rootField;

    public RootObj()  {
        this.rootField = "This is root field";
    }

    private RootObj(String text){   //note this is a private constructor
        this.rootField = text;
    }

    public void rootMethod1()  {
        System.out.println("This is root method1(public)");
    }

    private void rootMethod2() {    //note this is a private method
        System.out.println("This is root method2(private)");
    }
}
