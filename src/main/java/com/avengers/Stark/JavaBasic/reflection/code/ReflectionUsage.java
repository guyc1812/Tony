package com.avengers.Stark.JavaBasic.reflection.code;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUsage {

    public static void main(String args[]) throws Exception {


        // access class
        Class way1 = Class.forName("com.avengers.Stark.JavaBasic.reflection.code.ChildObj");
        Class way2 = ChildObj.class;

        ChildObj obj = new ChildObj();
        Class way3 = obj.getClass();


        // create a instance
        ChildObj copy1 = (ChildObj) way3.newInstance();

        Constructor cons = way3.getConstructor();
        ChildObj copy2 = (ChildObj) cons.newInstance();


        // getName()
        System.out.println("Class name is " + way3.getName());

        // getConstructors()
        Constructor[] constructors = way3.getConstructors();
        for (Constructor one : constructors) System.out.println("Constructor Name is " + one.getName());

        // getDeclaredConstructors()
        Constructor[] declaredConstructors = way3.getDeclaredConstructors();
        for (Constructor one : declaredConstructors) System.out.println("declaredConstructor Name is " + one.getName());


        // getConstructor()
        Constructor constructor = way3.getConstructor();
        System.out.println("Constructor(exact) name is " + constructor.getName());

        // getDeclaredConstructor()
        Constructor declaredConstructor = way3.getDeclaredConstructor(String.class);
        System.out.println("declaredConstructor(exact) name is " + declaredConstructor.getName());
        declaredConstructor.setAccessible(true);
        declaredConstructor.newInstance("a");

        // getMethods()
        Method[] methods = way3.getMethods();
        for (Method method : methods) System.out.println("Method Name is " + method.getName());

        // getDeclaredMethods()
        Method[] declaredMethods = way3.getDeclaredMethods();
        for (Method method : declaredMethods) System.out.println("DeclaredMethods Name is " + method.getName());

        // invoke a exactly method by knowing it's name and para types
        Method method2 = way3.getDeclaredMethod("childMethod2", int.class);
        Method method3 = way3.getDeclaredMethod("childMethod3");
        method3.setAccessible(true);

        // invokes the method at runtime
        method2.invoke(obj, 19);
        method3.invoke(obj);


        // getDeclaredFields()
        StringBuffer sb = new StringBuffer();
        sb.append(Modifier.toString(way3.getModifiers()) + " class " + way3.getSimpleName() + "{\n");
        Field[] fs = way3.getDeclaredFields();
        for (Field field : fs) {
            sb.append("\t");
            sb.append(Modifier.toString(field.getModifiers()) + " "); //获得属性的修饰符，例如public，static等等
            sb.append(field.getType().getSimpleName() + " ");       //属性的类型的名字
            sb.append(field.getName() + ";\n");                       //属性的名字+回车
        }
        sb.append("}");

        System.out.println(sb);

    }

}
