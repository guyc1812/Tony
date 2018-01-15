# Synchronized

### Threads and shared data

* each thread is awarded a Java stack, <br>
    which contains data no other thread can access, <br>
    including the local variables, parameters, and return values of each method the thread has invoked.<br>
    The data on the stack is limited to primitive types and object references. 
    
* There is only one heap inside the JVM, and all threads share it. <br>
    The heap contains nothing but objects(including arrays).
    
* The method area is similar to the stack in that it contains only primitive types and object references. <br>
    The method area contains all the class (or static) variables used by the program.


### Monitors

* The JVM uses locks in conjunction with monitors. <br>
* Each monitor is associated with an object reference. 

![monitor](../../imgs/java-monitor.gif)


### Synchronized blocks

* Synchronized statements.
    
    * monitorenter()
        acquire the lock of object<br>
        a count is incremented
        
    * monitorexit()
        the count is decremented<br>
        When the count reaches zero, the monitor is released

* Synchronized methods.
    
    * the JVM acquires a lock before invoking the method
    * whether method completes by returning or by throwing an exception, the lock is released.
    

### Differences between Lock and Synchronized block

* A synchronized block is fully contained within a method <br>
    The Lock API has lock() and unlock() operation in separate methods
* A synchronized block does not support the fairness
    The Lock APIs by specify the fairness property. It makes sure that longest waiting thread is given access to lock
* A thread gets blocked if it can’t get an access to the synchronized block. 
    The Lock API provides tryLock() method. 
    The thread acquires lock only if it’s available and not held by any other thread. 
    This reduces blocking time of thread waiting for the lock
* A thread which is in "waiting" state to acquire the access to synchronized block, can’t be interrupted. 
    The Lock API provides a method lockInterruptibly() which can be used to interrupt the thread when it is waiting for the lock.
* A synchronized block does not need to unlock
    The Lock needs to "unlock" in the finally block
* A synchronized is also a ReentrantLock type.



[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/lock/code)



### References:

[How the Java virtual machine performs thread synchronization](https://www.javaworld.com/article/2076971/java-concurrency/how-the-java-virtual-machine-performs-thread-synchronization.html)