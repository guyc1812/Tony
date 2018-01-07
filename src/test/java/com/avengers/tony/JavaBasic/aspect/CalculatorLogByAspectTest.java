package com.avengers.tony.JavaBasic.aspect;

import com.avengers.tony.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CalculatorLogByAspectTest extends BaseTest {

    @Autowired
    @Qualifier("calculator")
    Calculator calculator;

    @Test
    public void CalculatorLogByAspect() {

        int result = calculator.add(11, 12);
        System.out.println("result:" + result);

        result = calculator.div(21, 3);
        System.out.println("result:" + result);
    }


}
