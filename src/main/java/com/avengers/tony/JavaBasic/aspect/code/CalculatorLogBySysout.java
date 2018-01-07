package com.avengers.tony.JavaBasic.aspect.code;

import org.springframework.stereotype.Service;

@Service("calculatorSysoutLog")
public class CalculatorLogBySysout implements Calculator {

    @Override
    public int add(int i, int j) {
        System.out.println("[-- before --] The method add begins with [" + i + "," + j + "]");
        int result = i + j;
        System.out.println("[-- after ---] The method add ends with " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("[-- before --] The method sub begins with [" + i + "," + j + "]");
        int result = i - j;
        System.out.println("[-- after ---] The method sub ends with " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("[-- before --] The method mul begins with [" + i + "," + j + "]");
        int result = i * j;
        System.out.println("[-- after ---] The method mul ends with " + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("[-- before --] The method div begins with [" + i + "," + j + "]");
        int result = i / j;
        System.out.println("[-- after ---] The method div ends with " + result);
        return result;
    }

}
