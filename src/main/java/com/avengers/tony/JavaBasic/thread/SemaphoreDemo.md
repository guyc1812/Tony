# Semaphore


### Demo uses the ExecutorService

```
ExecutorService exec = Executors.newCachedThreadPool();
exec.execute(runnable <Runnable>);
```

### Runnable Instance

A runnable instance requires a Semaphore

```
private Runnable newRun(int NO, Semaphore semp){
    return () -> {
        try {
            semp.acquire();
            System.out.println(Thread.currentThread().getName()+" => Accessing: " + NO);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semp.release();
        }
    };
}
```


### Demo for Semaphore

Semaphore controls how many threads can be executed at a same time

```
public static void main(String[] args) {

    ExecutorService exec = Executors.newCachedThreadPool();

    // only five threads at the same time
    final Semaphore semp = new Semaphore(5);

    // Simulate 20 clients access
    for (int index = 0; index < 20; index++) {
        exec.execute(new SemaphoreDemo().newRun(index,semp));
    }

    exec.shutdown();
}
```

Output: 5 threads at a time

```
pool-1-thread-1 => Accessing: 0
pool-1-thread-4 => Accessing: 3
pool-1-thread-5 => Accessing: 4
pool-1-thread-3 => Accessing: 2
pool-1-thread-2 => Accessing: 1

pool-1-thread-6 => Accessing: 5
pool-1-thread-9 => Accessing: 8
pool-1-thread-8 => Accessing: 7
pool-1-thread-7 => Accessing: 6
pool-1-thread-10 => Accessing: 9

pool-1-thread-11 => Accessing: 10
pool-1-thread-15 => Accessing: 14
pool-1-thread-14 => Accessing: 13
pool-1-thread-13 => Accessing: 12
pool-1-thread-12 => Accessing: 11

pool-1-thread-16 => Accessing: 15
pool-1-thread-20 => Accessing: 19
pool-1-thread-19 => Accessing: 18
pool-1-thread-18 => Accessing: 17
pool-1-thread-17 => Accessing: 16
```