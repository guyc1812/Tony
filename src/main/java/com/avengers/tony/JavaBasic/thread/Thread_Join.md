# Join

### Source

```
public final synchronized void join(long millis) throws InterruptedException {
    long base = System.currentTimeMillis();
    long now = 0;

    if (millis < 0) {
        throw new IllegalArgumentException("timeout value is negative");
    }

    if (millis == 0) {
        while (isAlive()) {
            wait(0);
        }
    } else {
        while (isAlive()) {
            long delay = millis - now;
            if (delay <= 0) {
                break;
            }
            wait(delay);
            now = System.currentTimeMillis() - base;
        }
    }
}

public final void join() throws InterruptedException {
    join(0);
}
```


### Implementation

```
while (isAlive()) {
    wait(0);    // If the thread is alive, then wait 
}
```


### Demo

* A Runnable Class

    ```
    static class JoinRunnable implements Runnable {
        public void run() {
            try {
                Timer.start();
                System.out.println("[JoinThread]      START !");
                Thread.sleep(5000);
                System.out.print("[JoinThread]      FINISH --> ");
                Timer.end();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    ```
    
* A Thread Class With synchronized Block Which Lock The Thread Instance
    ```
    static class JoinThread_Sync extends Thread {
    
        Thread thread;

        public JoinThread_Sync(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            synchronized (this.thread) {
                Timer.start();
                System.out.println("[JoinThread_Sync] LOCK AND START !");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.print("[JoinThread_Sync] UNLOCK AND FINISH --> ");
                Timer.end();
            }
        }
    }
    ```


### Test join()

```
public static void main(String[] args){
    try {
        System.out.println("["+Thread.currentThread().getName()+ "] START !");
        Timer.start();
        Thread t = new Thread(new JoinRunnable());
        t.start();
        t.join();       // ==> change delay time     
        System.out.print("["+Thread.currentThread().getName()+ "] DONE --> ");
        Timer.end();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

1. `t.join();` delay = 0, thread running time(5000)<br>
    [main] wait until the [JoinThread] finish

    ```
    // Output:
    [main]            START !
    [JoinThread]      START !
    [JoinThread]      FINISH --> Timer : 5000 ms
    [main]            DONE --> Timer : 5000 ms
    ```

2. `t.join(1000);` delay < thread running time(5000)<br>
   [main] only wait [JoinThread] for 1000 ms

    ```
    // Output:
    [main]            START !
    [JoinThread]      START !
    [main]            DONE --> Timer : 1000 ms
    [JoinThread]      FINISH --> Timer : 5000 ms
    ```

3. `t.join(7000);` delay > thread running time(5000)<br>
    equivalent to `t.join();`
    
    ```
    // Output:
    [main]            START !
    [JoinThread]      START !
    [JoinThread]      FINISH --> Timer : 5000 ms
    [main]            DONE --> Timer : 5000 ms
    ```


### Test join() With A Thread Holding A Lock

```
public static void main(String[] args){

    try {
        System.out.println("["+Thread.currentThread().getName()+ "]            START !");
        Timer.start();
        Thread t = new Thread(new JoinRunnable());      // [JoinThread] instance pass to [JoinThread_Sync]
        Thread t_sync = new JoinThread_Sync(t);         // [JoinThread_Sync] hold a lock of [JoinThread] for 3000ms
        t_sync.start();                                 
        t.start();
        t.join();
        System.out.print("["+Thread.currentThread().getName()+ "]            DONE --> ");
        Timer.end();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

1. `t.join();` delay = 0 <br>
    [JoinThread] running time(5000)<br>
    [JoinThread_Sync] running time(3000)<br>
    [main] wait until the both [JoinThread] and [JoinThread_Sync] finish

    ```
    // Output:
    [main]            START !
    [JoinThread_Sync] LOCK AND START !
    [JoinThread]      START !
    [JoinThread_Sync] UNLOCK AND FINISH --> Timer : 3001 ms
    [JoinThread]      FINISH --> Timer : 5001 ms
    [main]            DONE --> Timer : 5001 ms
    ```

2. `t.join(10000);` delay > max{[JoinThread],[JoinThread_Sync]} running time(5000) <br>
   [main] wait max{[JoinThread],[JoinThread_Sync]} running time(5000)

    ```
    // Output:
    [main]            START !
    [JoinThread_Sync] LOCK AND START !
    [JoinThread]      START !
    [JoinThread_Sync] UNLOCK AND FINISH --> Timer : 3001 ms
    [JoinThread]      FINISH --> Timer : 5001 ms
    [main]            DONE --> Timer : 5001 ms
    ```

3. `t.join(2000);` delay < [JoinThread_Sync] running time(3000) <br>
    [main] wait at least [JoinThread_Sync] running time(5000)
    
    ```
    // Output:
    [main]            START !
    [JoinThread_Sync] LOCK AND START !
    [JoinThread]      START !
    [JoinThread_Sync] UNLOCK AND FINISH --> Timer : 3001 ms
    [main]            DONE --> Timer : 3001 ms
    [JoinThread]      FINISH --> Timer : 5000 ms
    ```

4. `t.join(4000);` [JoinThread] running time(5000) < delay < [JoinThread_Sync] running time(3000) <br>
    [main] wait delay(4000)
    
    ```
    // Output:
    [main]            START !
    [JoinThread_Sync] LOCK AND START !
    [JoinThread]      START !
    [JoinThread_Sync] UNLOCK AND FINISH --> Timer : 3001 ms
    [main]            DONE --> Timer : 4001 ms
    [JoinThread]      FINISH --> Timer : 5001 ms
    ```
    
    
[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/thread/code)