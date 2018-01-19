package com.avengers.tony.JavaBasic.ProducerConsumer.code;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StorageAwaitSignal implements Storage {

    private final int MAX_SIZE = 100;
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private LinkedList<Object> list = new LinkedList<>();

    public void produce(int num) {

        lock.lock();
        // if insufficient
        if (list.size() + num > MAX_SIZE) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for producing " + num + " items");
            try {
                full.await();   // await
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // produce num items
        for (int i = 1; i <= num; ++i) {
            list.add(new Object());
        }
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t produced " + num);

        // wake others
        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }

    public void consume(int num) {

        lock.lock();
        // if insufficient
        while (list.size() < num) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for consuming " + num + " items");
            try {
                empty.await();  // await
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // consume num items
        for (int i = 1; i <= num; ++i) {
            list.remove();
        }
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t consumed " + num);

        // wake others
        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }

}
