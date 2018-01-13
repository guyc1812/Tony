# ReentrantReadWriteLock


### Introduce

java.util.concurrent.locks.ReentrantReadWriteLock class has been introduced in JDK 1.5. 
ReentrantReadWriteLock is the implementation of ReadWriteLock. 

* Acquisition order
    ReentrantReadWriteLock can behave as fair and non-fair mode both. 
    The default behavior is non-fair. 
    The performance of non-fair lock is better though it is possible that a reader or writer lock can be postponed many times which are continuously trying to lock. 
    In case of fair lock the locking request is fulfilled in the order that either the longest waiting single writer lock or the group of read locks request, whosoever has longest waiting time will acquire lock on the shared resource. 

* Reentrant property
    ReentrantReadWriteLock provides reentrancy for both read and write lock. It means read and write lock both can be reacquired. 

* Lock downgrading
    The reentrancy in ReentrantReadWriteLock can be downgraded from write lock to read lock. It means if a thread has acquired write lock, it can downgrade its lock from write to read lock. The sequence will be that first acquire write lock, do the write operation and then acquire read lock and then unlock the write lock and after read operation finally unlock the read lock. 

* Main methods 
    readLock() : It requests for read operation lock. 
    writeLock(): It requests for write operation lock.


### Demo

```
public class ReentrantRDLockDemo {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static String message = "a";

    static class Read implements Runnable {

        public void run() {
            for (int i = 0; i <= 5; i++) {
                if (lock.isWriteLocked()) {
                    System.out.println("I'll take the lock from Write");
                }
                lock.readLock().lock();
                System.out.println("R " + Thread.currentThread().getName() + " ---> Message is " + message);
                lock.readLock().unlock();
            }
        }
    }

    static class Write implements Runnable {

        public void run() {
            for (int i = 0; i <= 5; i++) {
                try {
                    lock.writeLock().lock();
                    System.out.println("W " + Thread.currentThread().getName() + " ---> Message is " + message);
                    message = message.concat("a");
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }

}
```

Test

```
public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(4);
    pool.execute(new Read());
    pool.execute(new Read());
    pool.execute(new Write());
    pool.execute(new Write());
    pool.shutdown();
}
```

Output:

```
// read threads is alternant
R pool-1-thread-1 ---> Message is a
R pool-1-thread-2 ---> Message is a
R pool-1-thread-1 ---> Message is a
R pool-1-thread-2 ---> Message is a
R pool-1-thread-1 ---> Message is a
R pool-1-thread-2 ---> Message is a
R pool-1-thread-1 ---> Message is a
R pool-1-thread-2 ---> Message is a
R pool-1-thread-1 ---> Message is a
R pool-1-thread-2 ---> Message is a
R pool-1-thread-1 ---> Message is a
R pool-1-thread-2 ---> Message is a

W pool-1-thread-3 ---> Message is a
W pool-1-thread-3 ---> Message is aa
W pool-1-thread-3 ---> Message is aaa
W pool-1-thread-3 ---> Message is aaaa
W pool-1-thread-3 ---> Message is aaaaa
W pool-1-thread-3 ---> Message is aaaaaa
W pool-1-thread-4 ---> Message is aaaaaaa
W pool-1-thread-4 ---> Message is aaaaaaaa
W pool-1-thread-4 ---> Message is aaaaaaaaa
W pool-1-thread-4 ---> Message is aaaaaaaaaa
W pool-1-thread-4 ---> Message is aaaaaaaaaaa
W pool-1-thread-4 ---> Message is aaaaaaaaaaaa
```