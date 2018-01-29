# StampedLock

* New in Java 8 
* Similar to the ReentrantReadWriteLock. 
* Allows for optimistic reads, which is not supported by the ReentrantReadWriteLock. 


### StampedLocks vs. ReadWriteLocks and Synchronized

1. Synchronized always lead a good performance, use this much.
2. readers is much more than writers: lock.tryOptimisticRead() is the best.
3. readers and writers at a same number level: StampedLock is the best.
4. When over 10 threads, ReadWriteLocks is always the worst.


### Demo

```
public static void main(String[] args) {

    ExecutorService executor = Executors.newFixedThreadPool(10);

    Map<String, String> map = new HashMap<>();

    StampedLock lock = new StampedLock();

    Runnable writeTask = () -> {
        long stamp = lock.writeLock();
        try {
            map.put("foo", "bar");
            System.out.println(Thread.currentThread().getName() + " " + " stamp: " + stamp + " writing");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }
    };

    Runnable readTask = () -> {
        long stamp = lock.readLock();
        try {
            System.out.println(Thread.currentThread().getName() + " " + " stamp: " + stamp + " reading: " + map.get("foo"));
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamp);
        }
    };

    executor.submit(writeTask);

    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(writeTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);

    executor.shutdown();

}
```

### Demo Test

```
pool-1-thread-1  stamp: 384 writing
pool-1-thread-2  stamp: 513 reading: bar
pool-1-thread-3  stamp: 515 reading: bar
pool-1-thread-4  stamp: 514 reading: bar
pool-1-thread-5  stamp: 640 writing
pool-1-thread-8  stamp: 771 reading: bar
pool-1-thread-7  stamp: 772 reading: bar
pool-1-thread-6  stamp: 769 reading: bar
pool-1-thread-9  stamp: 770 reading: bar
```

### Demo For Optimistic

```
public static void main(String[] args) {

    ExecutorService executor = Executors.newFixedThreadPool(2);

    StampedLock lock = new StampedLock();

    executor.submit(() -> {
        long stamp = lock.tryOptimisticRead();  // non blocking
        // do some read
        try {
            System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            Thread.sleep(300);
            System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            Thread.sleep(300);
            System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            Thread.sleep(300);
            System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            Thread.sleep(300);
            System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            if (!lock.validate(stamp)) {        // if a write occurred, try again with a read lock
                long readStamp = lock.readLock();
                try {
                    // do some read
                } finally {
                    lock.unlock(readStamp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(stamp);
        }
    });

    executor.submit(() -> {
        long stamp = lock.writeLock();
        try {
            System.out.println("Write Lock acquired");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(stamp);
            System.out.println("Write done");
        }
    });

    executor.shutdown();
}
```

### Demo Test For Optimistic

```
Optimistic Lock Valid: true
Write Lock acquired
Optimistic Lock Valid: false
Optimistic Lock Valid: false
Optimistic Lock Valid: false
Write done
Optimistic Lock Valid: false
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/lock/code)


### Reference:

[Java 8 StampedLocks vs. ReadWriteLocks and Synchronized](https://blog.takipi.com/java-8-stampedlocks-vs-readwritelocks-and-synchronized/)