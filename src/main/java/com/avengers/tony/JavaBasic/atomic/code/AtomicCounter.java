package com.avengers.tony.JavaBasic.atomic.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private AtomicInteger value = new AtomicInteger();

    private Integer k = 0;

    public Integer getK() {
        return k;
    }

    public Integer kPlusTwo(){
        k=k+2;
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
        ExecutorService service = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 30; i++) {
            service.execute(() -> {
//                System.out.println(Thread.currentThread().getName()+"\t k is -------> "+counter.increase(2));
                System.out.println(Thread.currentThread().getName()+"\t k plus two -> "+counter.kPlusTwo());
            });
        }
        service.shutdown();
    }

}



//pool-1-thread-1	 k plus two -> 2
//pool-1-thread-5	 k plus two -> 8
//pool-1-thread-4	 k plus two -> 6
//pool-1-thread-7	 k plus two -> 12
//pool-1-thread-3	 k plus two -> 4
//pool-1-thread-2	 k plus two -> 2