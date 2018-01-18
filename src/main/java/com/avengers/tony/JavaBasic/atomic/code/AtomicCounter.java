package com.avengers.tony.JavaBasic.atomic.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
        //  return value.getAndIncrement();
    }

    public int increase(int i) {
        return value.addAndGet(i);      // use for(;;) and compareAndSet(current, next)
        //  return value.getAndAdd(i);
    }

    public int decrease() {
        return value.decrementAndGet(); // use for(;;) and compareAndSet(current, next)
        //  return value.getAndDecrement();
    }

    public int decrease(int i) {
        return value.addAndGet(-i);     // use for(;;) and compareAndSet(current, next)
        //  return value.addAndGet(-i);
    }

    public static void main(String[] args) {
        final AtomicCounter counter = new AtomicCounter();
        ExecutorService service = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 9; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"\t atomic plus two -> "+counter.increase(2));
//                System.out.println(Thread.currentThread().getName() + "\t k simple plus two -> " + counter.kPlusTwo());
            });
        }
        service.shutdown();
    }

}