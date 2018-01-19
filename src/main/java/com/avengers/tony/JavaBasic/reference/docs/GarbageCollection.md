# Garbage Collection


### Introduction
 
* Main objective of Garbage Collector is to free heap memory by destroying unreachable objects.
* Garbage collector is best example of [Daemon thread]() as it is always running in background.


### Conceptions

* Unreachable objects  
    An object is said to be unreachable if it does not contain any reference to it. 
    Also note that objects which are part of island of isolation are also unreachable.
    
* Eligibility for garbage collection  
    An object is said to be eligible for GC if it is unreachable. 
    

### four different ways to make an object eligible for GC.

* Nullifying the reference variable
* Re-assigning the reference variable
* Object created inside method
* Island of Isolation

[See Ways for Garbage Collection](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/reference/WaysForGC.md)



### Request JVM to run Garbage Collector    

* `System.gc();`
* `Runtime.getRuntime().gc();`
         
        
### Finalization

* Before destroying an object, Garbage Collector calls finalize() method on the object to perform cleanup activities. 
    Once finalize() method completes, Garbage Collector destroys that object.
    
* finalize() method is present in Object class with following prototype.
    `protected void finalize() throws Throwable`
    
* Based on our requirement, we can override finalize() method for perform our cleanup activities.
    like closing connection from database.
    
* The finalize() method called by Garbage Collector not JVM.

* If an uncaught exception is thrown by the finalize() method, 
    the exception is ignored and finalization of that object terminates.
    

### GC in physical

// todo



### Reference

[Garbage Collection in Java](https://www.geeksforgeeks.org/garbage-collection-java)

[Understanding Java Garbage Collection](https://www.cubrid.org/blog/understanding-java-garbage-collection)