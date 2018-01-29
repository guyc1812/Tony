package com.avengers.tony.JavaBasic.aspect;

import com.avengers.tony.BaseTest;
import com.avengers.Stark.JavaBasic.aspect.code.Calculator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CalculatorLogByAspectJTest extends BaseTest {

    @Autowired
    @Qualifier("calculator")
    Calculator calculator;

    @Test
    public void CalculatorLogByAspect() {

        int result1 = calculator.add(11, 12);

        int result2 = calculator.div(21, 0);
    }


}
