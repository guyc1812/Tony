# ExecutorService

### class ExecutorService 

```
- execute(Runnable) => return void
- submit(Runnable)  => return null if the task has finished correctly.
- submit(Callable)  => returns Callable result if the task has finished correctly.
- invokeAny(List<callable>)    => randomly pick a thread in the list
- invokeAll(List<callable>)    => run all the threads
```

### class ThreadPoolExecutor extends AbstractExecutorService implements ExecutorService

* corePoolSize

    the number of threads to keep in the pool, even if they are idle.
     
* maximumPoolSize

    the maximum number of threads to allow in the pool.

* keepAliveTime

    when the number of threads is greater than the core, <br>
    this is the maximum time that excess idle threads will wait for new tasks before terminating.

* unit

    the time unit for the keepAliveTime argument: `TimeUnit.MILLISECONDS`

* workQueue

    the queue to use for holding tasks before they are executed.  <br>
    This queue will hold only the Runnable tasks submitted by the execute method.

* threadFactory

    the factory to use when the executor creates a new thread.
    
    DefaultThreadFactory: `pool-N-thread`

* handler

    he handler to use when execution is blocked,
    when the thread bounds and queue capacities are reached.
    
    defaultHandler: throws `RejectedExecutionException` always

    ```
    public ThreadPoolExecutor(
        int corePoolSize,
        int maximumPoolSize,
        long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue,
        ThreadFactory threadFactory,
        RejectedExecutionHandler handler
    )
    ```


### Static methods in Executors (Return ExecutorService) 

* `ExecutorService executorService1 = Executors.newSingleThreadExecutor();`
    
    ```
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService(
            new ThreadPoolExecutor(
                1, 
                1,
                0L, 
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
            )
        );
    }
    
    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return new FinalizableDelegatedExecutorService(
            new ThreadPoolExecutor(
                1, 
                1,
                0L, 
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()，
                threadFactory
            )
        );
    }
    ```

* `ExecutorService executorService2 = Executors.newFixedThreadPool(10);`

    ```
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(
            nThreads, 
            nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>()
        );
    }
    
    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(
            nThreads, 
            nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            threadFactory
        );
    }
    ```

* `ExecutorService executorService3 = Executors.newScheduledThreadPool(10);`

    ```
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
                             new DelayedWorkQueue());
    }
    
    public static ScheduledExecutorService newScheduledThreadPool(
            int corePoolSize, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }
    ```

* `ExecutorService executorService4 = Executors.newCachedThreadPool();`

    ```
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(
            0, 
            Integer.MAX_VALUE,
            60L, 
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>()
        );
    }
    
    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(
            0, 
            Integer.MAX_VALUE,
            60L, 
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>()，
            threadFactory
        );
    }
    ```

### ScheduledExecutorService

```
schedule(Runnable command, long delay, TimeUnit unit);
schedule(Callable<V> callable, long delay, TimeUnit unit);
scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/thread/code)