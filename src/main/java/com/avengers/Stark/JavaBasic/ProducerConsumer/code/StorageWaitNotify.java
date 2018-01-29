package com.avengers.Stark.JavaBasic.ProducerConsumer.code;

import java.util.LinkedList;

public class StorageWaitNotify implements Storage {

    private final int MAX_SIZE = 100;
    private LinkedList<Object> list = new LinkedList<>();

    @Override
    public void produce(int num) {
        synchronized (list) {
            // if insufficient
            while (list.size() + num > MAX_SIZE) {
                System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for producing " + num + " items");
                try {
                    list.wait();    // wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // produce num items
            for (int i = 1; i <= num; ++i) {
                list.add(new Object());
            }
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t produced " + num);
            list.notifyAll();
        }
    }

    @Override
    public void consume(int num) {
        synchronized (list) {
            // if insufficient
            while (list.size() < num) {
                System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for consuming " + num + " items");
                try {
                    list.wait();    // wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // consume num items
            for (int i = 1; i <= num; ++i) {
                list.remove();
            }
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t consumed " + num);
            list.notifyAll();
        }
    }
}

