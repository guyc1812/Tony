package com.avengers.tony.JavaBasic.ProducerConsumer.code;


import java.util.concurrent.LinkedBlockingQueue;

public class Storage_BlockingQueue implements Storage {

    private final int MAX_SIZE = 100;
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(MAX_SIZE);

    public void produce(int num) {


        if (list.size() == MAX_SIZE) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: full produce failed");
            return;
        }
        // produce num items
        for (int i = 1; i <= num; ++i) {
            try {
                list.put(i);     // block automatically
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(list);
        System.out.println(list.size());
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t produced " + num);
    }

    public void consume(int num) {

        if (list.size() == 0) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: empty consume failed");
            return;
        }
        // consume num items
        for (int i = 1; i <= num; ++i) {
            try {
                list.take();                // block automatically
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t consumed " + num);
    }

}
