# volatile

### Introduce

In Java, each thread has its own stack, <br>
including its own copy of variables it can access. <br>
When the thread is created, <br>
it copies the value of all accessible variables into its own stack. 

The volatile keyword basically says to the JVM "Warning, this variable may be modified in another Thread".

In all versions of Java, <br>
the volatile keyword guarantees global ordering on reads and writes to a variable. <br>
This implies that every thread accessing a volatile field will read the variable’s current value instead of (potentially) using a cached value.

In Java 5 or later, <br>
volatile reads and writes establish a happens-before relationship, <br>
much like acquiring and releasing a mutex.

Using volatile may be faster than a lock, <br>
but it will not work in some situations. <br>
The range of situations in which volatile is effective was expanded in Java 5, <br>
in particular, double-checked locking now works correctly.

The volatile keyword is also useful for 64-bit types like long and double since they are written in two operations. <br>
Without the volatile keyword you risk stale or invalid values.

One common example for using volatile is for a flag to terminate a thread. <br>
If you’ve started a thread, and you want to be able to safely interrupt it from a different thread, <br>
you can have the thread periodically check a flag (i.e., to stop it, set the flag to true). <br>
By making the flag volatile, <br>
you can ensure that the thread that is checking its value will see that it has been set to true without even having to use a synchronized block. 

```
public class Foo extends Thread {
    private volatile boolean close = false;
    public void run() {
        while(!close) {
            // do work
        }
    }
    public void close() {
        close = true;
        // interrupt here if needed
    }
}
```

### Singleton 

```
public class Singleton {

    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```