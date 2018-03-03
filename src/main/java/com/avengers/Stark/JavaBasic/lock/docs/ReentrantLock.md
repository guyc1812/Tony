# Reentrant Lock


### What are Reentrant Locks

* The ReentrantLock class implements the Lock interface and provides synchronization to methods while accessing shared resources. 
* ReentrantLock allow threads to enter into lock on a resource more than once. 
    When the thread first enters into lock, a hold count is set to one. 
    Before unlocking the thread can re-enter into lock again and every time hold count is incremented by one. 
    For every unlock request, hold count is decremented by one and when hold count is 0, the resource is unlocked.
* Reentrant Locks also offer a fairness parameter.
     This fairness mode is set up by passing true to the constructor of the lock.
     

### ReentrantLock Methods
    
* lock(): 
    Call to the lock() method increments the hold count by 1 and gives the lock to the thread if the shared resource is initially free.
    
* unlock(): 
    Call to the unlock() method decrements the hold count by 1. 
    When this count reaches zero, the resource is released.
    
* tryLock(): 
    If the resource is not held by any other thread, then call to tryLock() returns true and the hold count is incremented by one. 
    If the resource is not free then the method returns false and the thread is not blocked but it exits.
    
* tryLock(long timeout, TimeUnit unit): 
    As per the method, the thread waits for a certain time period as defined by arguments of the method to acquire the lock on the resource before exiting.
    
* lockInterruptibly(): 
    This method acquires the lock if the resource is free while allowing for the thread to be interrupted by some other thread while acquiring the resource. 
    It means that if the current thread is waiting for lock but some other thread requests the lock, 
    then the current thread will be interrupted and return immediately without acquiring lock.
    
* getHoldCount(): 
    This method returns the count of the number of locks held on the resource.
    
* isHeldByCurrentThread(): 
    This method returns true if the lock on the resource is held by the current thread.
    

### Demo

```java
static class worker implements Runnable {
    String name;
    ReentrantLock re;

    public worker(ReentrantLock re, String name) {
        this.re = re;
        this.name = name;
    }

    public void run(){
    
        boolean done = false;
        while (!done) {

            boolean ans = re.tryLock();

            // Returns True if lock is free
            if (ans) {

                try {

                    System.out.println(this.name + " is in");
                    System.out.println(this.name + " is working on job-1");
                    System.out.println(this.name + " is holding lock count-" + re.getHoldCount());

                    re.lock();

                    try {
                        System.out.println(this.name + " is working on job-2");
                        System.out.println(this.name + " is holding lock count-" + re.getHoldCount());
                        Thread.sleep(1500);
                        System.out.println(this.name + " is done job-2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        re.unlock();
                    }
                    System.out.println(this.name + " is holding lock count-" + re.getHoldCount());
                    System.out.println(this.name + " is done job-1");

                    done = true;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    re.unlock();
                    System.out.println(this.name + " is holding lock count-" + re.getHoldCount());
                    System.out.println(this.name + " is out");
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

Test

```java
public static void main(String[] args) {
    ReentrantLock rel = new ReentrantLock();
    ExecutorService pool = Executors.newFixedThreadPool(4);
    Runnable w1 = new worker(rel, "worker-1");
    Runnable w2 = new worker(rel, "worker-2");
    Runnable w3 = new worker(rel, "worker-3");
    Runnable w4 = new worker(rel, "worker-4");
    pool.execute(w1);
    pool.execute(w2);
    pool.execute(w3);
    pool.execute(w4);
    pool.shutdown();
}
```

Output:

```bash
worker-1 is in
worker-1 is working on job-1
worker-1 is holding lock count-1
worker-1 is working on job-2
worker-1 is holding lock count-2
worker-1 is done job-2
worker-1 is holding lock count-1
worker-1 is done job-1
worker-1 is holding lock count-0
worker-1 is out

worker-3 is in
worker-3 is working on job-1
worker-3 is holding lock count-1
worker-3 is working on job-2
worker-3 is holding lock count-2
worker-3 is done job-2
worker-3 is holding lock count-1
worker-3 is done job-1
worker-3 is holding lock count-0
worker-3 is out

worker-4 is in
worker-4 is working on job-1
worker-4 is holding lock count-1
worker-4 is working on job-2
worker-4 is holding lock count-2
worker-4 is done job-2
worker-4 is holding lock count-1
worker-4 is done job-1
worker-4 is holding lock count-0
worker-4 is out

worker-2 is in
worker-2 is working on job-1
worker-2 is holding lock count-1
worker-2 is working on job-2
worker-2 is holding lock count-2
worker-2 is done job-2
worker-2 is holding lock count-1
worker-2 is done job-1
worker-2 is holding lock count-0
worker-2 is out
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/lock/code)



### Reference

[Reentrant Lock in Java](https://www.geeksforgeeks.org/reentrant-lock-java)