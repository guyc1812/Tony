# Start and Run Method

### Demo

* thread.start()
    
    correspond to the child thread
    
* thread.run()

    correspond to the current thread


```
public static void main(String args[]) {

        String series = "010203040506";
        Semaphore sem = new Semaphore(1);
        MultiThreads_Runnable R1 = new MultiThreads_Runnable(sem, "1",series);
        MultiThreads_Runnable R2 = new MultiThreads_Runnable(sem, "2",series);
        MultiThreads_Runnable R3 = new MultiThreads_Runnable(sem, "3",series);
        Thread t1 = new Thread(R1);
        Thread t2 = new Thread(R2);
        Thread t3 = new Thread(R3);
        t1.start();
        t2.start();
        t3.start();
        t3.run();       // run()
}

```

Output

```
main START !
[thread]3:135

Thread-2 START !
[thread]3:135

Thread-0 START !
[thread]1:000000

Thread-1 START !
[thread]2:246
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/thread/code)