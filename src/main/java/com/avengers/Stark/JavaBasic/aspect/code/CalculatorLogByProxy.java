package com.avengers.Stark.JavaBasic.aspect.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorLogByProxy {

    // target to be proxied
    private Calculator target;

    public CalculatorLogByProxy(Calculator target) {
        this.target = target;
    }

    // return the target
    public Calculator CalcLoggingProxy() {

        /*
         * loader:      classLoader of the target。
         * interfaces:  type of the target, in order to get it's methods
         * handler:     invoke method of InvocationHandler to handle the specific methods of target
         */
        ClassLoader loader = target.getClass().getClassLoader();
        Class[] interfaces = new Class[]{Calculator.class};
        InvocationHandler handler = new InvocationHandler() {

            /**
             * proxy:   proxy object
             * method:  method to be proxied
             * args:    args passed to the method
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object result = null;

                try {
                    // Before advice
                    System.out.println("[-- before advice -----------] The method " + method.getName() + " begins with " + Arrays.asList(args));

                    // invoke the specific method
                    result = method.invoke(target, args);

                    // After returning advice, with return value
                    System.out.println("[-- After returning advice --] The method " + method.getName() + " ends with " + result);

                } catch (Exception e) {

                    // After throwing advice
                    System.out.println("[-- After throwing advice ---] The method " + method.getName() + " throws exception");

                } finally {

                    // After (finally) advice, without return value or exception
                    System.out.println("[-- after advice ------------] The method " + method.getName() + " finish.");

                }

                return result;
            }
        };

        Calculator proxy = (Calculator) Proxy.newProxyInstance(loader, interfaces, handler);
        return proxy;
    }
}
