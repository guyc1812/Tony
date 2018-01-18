# StampedLock

* New in Java 8 
* Similar to the ReentrantReadWriteLock. 
* Allows for optimistic reads, which is not supported by the ReentrantReadWriteLock. 


### StampedLocks vs. ReadWriteLocks and Synchronized

1. Synchronized always lead a good performance, use this much.
2. readers is much more than writers: lock.tryOptimisticRead() is the best.
3. readers and writers at a same number level: StampedLock is the best.
4. When over 10 threads, ReadWriteLocks is always the worst.


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/lock/code)


### Reference:

[Java 8 StampedLocks vs. ReadWriteLocks and Synchronized](https://blog.takipi.com/java-8-stampedlocks-vs-readwritelocks-and-synchronized/)