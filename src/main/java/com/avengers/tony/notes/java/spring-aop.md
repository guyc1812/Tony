# AOP

Aspect-Oriented Programming (AOP) complements Object-Oriented Programming (OOP) by providing another way of thinking about program structure. <br>
The key unit of modularity in OOP is the class, whereas in AOP the unit of modularity is the aspect.


# AOP concepts

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


# Types of advice:

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
