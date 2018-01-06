package com.avengers.tony.JavaBasic.aspect;

import org.junit.Test;

public class CalculatorLogByProxyTest {

    @Test
    public void ArithmeticCalculatorLoggingProxy() {

        Calculator calculator = new CalculatorImpl();

        calculator = new CalculatorLogByProxy(calculator).getLoggingProxy();

        int result = calculator.add(11, 12);
        System.out.println("result:" + result);

        result = calculator.div(21, 3);
        System.out.println("result:" + result);
    }

}
