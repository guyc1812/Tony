package com.avengers.tony.JavaBasic.atomic.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yucgu on 2018/1/17.
 */
public class NonAtomic {

    private Integer k = 0;

    public Integer getK() {
        return k;
    }

    public Integer kPlusTwo(){
        k=k+2;
        return k;
    }

    public static void main(String[] args) {
        final NonAtomic counter = new NonAtomic();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 9; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"\t k plus two -> "+counter.kPlusTwo());
            });
        }
        service.shutdown();
    }
}
