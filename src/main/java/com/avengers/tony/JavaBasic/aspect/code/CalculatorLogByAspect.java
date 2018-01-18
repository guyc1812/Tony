package com.avengers.tony.JavaBasic.aspect.code;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 1. CoreConfig add @EnableAspectJAutoProxy
 * 2. add @Component to declare a bean
 * 3. add @Aspect to declare this bead is a aspect
 * 4. add @Order to declare the priority, 0 has the most priority.
 */
@Order(1)
@Aspect
@Component
public class CalculatorLogByAspect {

    /**
     * Declare a Pointcut
     */
    @Pointcut("execution(* com.avengers.tony.JavaBasic.aspect.code.CalculatorImpl.*(..))")
    public void declareJointPointExpression() {
    }


    /**
     * if no @Pointcut =>
     *
     * @Before("execution(public int com.tony.example.aspect.CalculatorImpl.*(int, int))")
     */
    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("[-- before ------] The method " + methodName + " begins with " + Arrays.asList(args));
    }

    @After("declareJointPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[-- after -------] The method " + methodName + " ends");
    }

    @AfterReturning(value = "declareJointPointExpression()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[-- return ------] The method " + methodName + " ends with " + result);
    }


    @AfterThrowing(value = "declareJointPointExpression()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[-- exception ---] The method " + methodName + " occurs exception:" + e);
    }

//    @Around("declareJointPointExpression()")
//    public Object aroundMethod(ProceedingJoinPoint pjd){
//
//        Object result = null;
//        String methodName = pjd.getSignature().getName();
//
//        try {
//            //bfaore
//            System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
//            //invoke
//            result = pjd.proceed();
//            //return
//            System.out.println("The method " + methodName + " ends with " + result);
//        } catch (Throwable e) {
//            //throw
//            System.out.println("The method " + methodName + " occurs exception:" + e);
//            throw new RuntimeException(e);
//        }finally {
//            //after
//            System.out.println("The method " + methodName + " ends");
//        }
//
//        return result;
//    }


}
