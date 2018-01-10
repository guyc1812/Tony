# AOP - Aspect Oriented Programming

AOP complements Object-Oriented Programming (OOP) by providing another way of thinking about program structure. <br>
The key unit of modularity in OOP is the class, whereas in AOP the unit of modularity is the aspect.

### AOP concepts

* Aspect
    
    A modularization of a concern that cuts across multiple classes. <br>
    Transaction management is a good example of a crosscutting concern in J2EE applications. <br>
    In Spring AOP, aspects are implemented using regular classes (the schema-based approach) or regular classes annotated with the @Aspect annotation (the @AspectJ style).

* Join point
    
    A point during the execution of a program, such as the execution of a method or the handling of an exception.<br> 
    In Spring AOP, a join point always represents a method execution.

* Advice

    Action taken by an aspect at a particular join point. <br>
    Different types of advice include "around," "before" and "after" advice.

* Pointcut
    
    A predicate that matches join points. <br>
    Advice is associated with a pointcut expression and runs at any join point matched by the pointcut. <br>
    The concept of join points as matched by pointcut expressions is central to AOP, and Spring uses the AspectJ pointcut expression language by default.


### Types of advice:

* Before advice

    Advice that executes before a join point, but which does not have the ability to prevent execution flow proceeding to the join point (unless it throws an exception).

* After returning advice
    
    Advice to be executed after a join point completes normally: <br>
    for example, if a method returns without throwing an exception.

* After throwing advice 
    
    Advice to be executed if a method exits by throwing an exception.

* After (finally) advice

    Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).

* Around advice

    Advice that surrounds a join point such as a method invocation. <br>
    This is the most powerful kind of advice. <br>
    Around advice can perform custom behavior before and after the method invocation. <br>
    It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.


### Usage of AspectJ

* Use with Annotation

    ```
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
    
        @Around("declareJointPointExpression()")
        public Object aroundMethod(ProceedingJoinPoint pjd){
    
            Object result = null;
            String methodName = pjd.getSignature().getName();
    
            try {
                //bfaore
                System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
                //invoke
                result = pjd.proceed();
                //return
                System.out.println("The method " + methodName + " ends with " + result);
            } catch (Throwable e) {
                //throw
                System.out.println("The method " + methodName + " occurs exception:" + e);
                throw new RuntimeException(e);
            }finally {
                //after
                System.out.println("The method " + methodName + " ends");
            }
    
            return result;
        }
    
    }
    ```

* Test

    ```
    @Autowired
    @Qualifier("calculator")
    Calculator calculator;
    
    @Test
    public void CalculatorLogByAspect() {
    
        int result1 = calculator.add(11, 12);
            //[-- before ------] The method add begins with [11, 12]
            //[-- after -------] The method add ends
            //[-- return ------] The method add ends with 23
    
            // @Around
            // The method add begins with [11, 12]
            // The method add ends with 23
            // The method add ends
            
        int result2 = calculator.div(21, 0);
            //[-- before ------] The method div begins with [21, 0]
            //[-- after -------] The method div ends
            //[-- exception ---] The method div occurs exception:java.lang.ArithmeticException: / by zero
            
            // @Around
            // The method div begins with [21, 0]
            // The method div occurs exception:java.lang.ArithmeticException: / by zero
            // The method div ends
    }
    ```
    

### Reference:

[Chapter 6. Aspect Oriented Programming with Spring](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html)


