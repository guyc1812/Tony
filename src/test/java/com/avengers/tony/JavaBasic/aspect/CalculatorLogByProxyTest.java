package com.avengers.tony.JavaBasic.aspect;

import com.avengers.Stark.JavaBasic.aspect.code.Calculator;
import com.avengers.Stark.JavaBasic.aspect.code.CalculatorImpl;
import com.avengers.Stark.JavaBasic.aspect.code.CalculatorLogByProxy;
import org.junit.Test;

public class CalculatorLogByProxyTest {

    @Test
    public void ArithmeticCalculatorLoggingProxy() {

        Calculator calculator = new CalculatorImpl();

        calculator = new CalculatorLogByProxy(calculator).CalcLoggingProxy();

        int result1 = calculator.add(11, 12);

        int result2 = calculator.div(21, 0);

    }

}
