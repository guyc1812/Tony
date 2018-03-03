# Atomic for Counter

### Demo

```java
public class AtomicCounter {

    private AtomicInteger value = new AtomicInteger();

    private Integer k = 0;

    public Integer kPlusTwo() {
        k = k + 2;
        return k;
    }

    public int getValue() {
        return value.get();
    }

    public int increase() {
        return value.incrementAndGet(); // use for(;;) and compareAndSet(current, next)
    }

    public int increase(int i) {
        return value.addAndGet(i);      // use for(;;) and compareAndSet(current, next)
    }

    public int decrease() {
        return value.decrementAndGet(); // use for(;;) and compareAndSet(current, next)
    }

    public int decrease(int i) {
        return value.addAndGet(-i);     // use for(;;) and compareAndSet(current, next)
    }

}
```

### Demo Test

```java
public static void main(String[] args) {
    final AtomicCounter counter = new AtomicCounter();
    ExecutorService service = Executors.newFixedThreadPool(50);
    for (int i = 0; i < 50; i++) {
        service.execute(() -> {
            //System.out.println(Thread.currentThread().getName()+"\t atomic plus two -> "+counter.increase(2));    //test-with-atomic
            System.out.println(Thread.currentThread().getName() + "\t k simple plus two -> " + counter.kPlusTwo()); //test-without-atomic
        });
    }
    service.shutdown();
}
```

### Output for test-without-atomic

```bash
pool-1-thread-1	 k simple plus two -> 2         // error
pool-1-thread-5	 k simple plus two -> 8
pool-1-thread-4	 k simple plus two -> 6
pool-1-thread-3	 k simple plus two -> 4
pool-1-thread-2	 k simple plus two -> 2         // error
pool-1-thread-6	 k simple plus two -> 12
pool-1-thread-8	 k simple plus two -> 10
pool-1-thread-9	 k simple plus two -> 14
pool-1-thread-7	 k simple plus two -> 16
```

### Output for test-with-atomic

```bash
pool-1-thread-2	 atomic plus two -> 2
pool-1-thread-4	 atomic plus two -> 10
pool-1-thread-5	 atomic plus two -> 8
pool-1-thread-3	 atomic plus two -> 6
pool-1-thread-1	 atomic plus two -> 4
pool-1-thread-8	 atomic plus two -> 16
pool-1-thread-6	 atomic plus two -> 12
pool-1-thread-7	 atomic plus two -> 14
pool-1-thread-9	 atomic plus two -> 18          // maybe no order, but safe
```

[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/atomic/code)
