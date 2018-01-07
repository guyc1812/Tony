package com.avengers.tony.JavaBasic.aspect;

import com.avengers.tony.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CalculatorLogBySysoutTest extends BaseTest {

    @Autowired
    @Qualifier("calculatorSysoutLog")
    Calculator calculatorLog;

    @Test
    public void CalculatorWithLog() {

        int result = calculatorLog.add(11, 12);
        System.out.println("result:" + result);

        result = calculatorLog.div(21, 3);
        System.out.println("result:" + result);
    }
}
