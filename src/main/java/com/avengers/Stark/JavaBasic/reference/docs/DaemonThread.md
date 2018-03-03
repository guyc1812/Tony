# Daemon Thread

Daemon threads in Java are like a service providers for other threads or objects running in the same process as the daemon thread. <br>
Daemon threads are used for background supporting tasks and are only needed while normal threads are executing.  <br>
If normal threads are not running and remaining threads are daemon threads then the interpreter exits. <br>

### Properties

* JVM terminates itself when all user threads finish their execution
* It is an utmost low priority thread.


### Usage

* void setDaemon(boolean status)
* boolean isDaemon()

Tip: We cannot call the setDaemon() method after starting the thread.

```java
public class DaemonThread extends Thread {

    public void run() { 
        // Checking whether the thread is Daemon or not
        if (Thread.currentThread().isDaemon()) {
            System.out.println("This is Daemon thread");
        } else {
            System.out.println("This is User thread");
        }
    }

    public static void main(String[] args) {

        DaemonThread t1 = new DaemonThread();
        DaemonThread t2 = new DaemonThread();
        DaemonThread t3 = new DaemonThread();

        // Setting user thread t1 to Daemon
        t1.setDaemon(true);

        // starting all the threads
        t1.start();
        t2.start();
        t3.start();
        
        // t3.setDaemon(true);
        // Exception in thread "main" java.lang.IllegalThreadStateException
        // We cannot call the setDaemon() method after starting the thread.
        
    }
}
```

### Daemon vs User Threads

* Priority
    
    When the only remaining threads in a process are daemon threads, the interpreter exits. This makes sense because when only daemon threads remain, there is no other thread for which a daemon thread can provide a service.
    
* Usage
    
    Daemon thread is to provide services to user thread for background supporting task.
    

[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/reference/code)


### Reference

[Daemon thread in Java](https://www.geeksforgeeks.org/daemon-thread-java/)