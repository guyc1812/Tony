package com.avengers.Stark.JavaBasic.lock.code.TicketLock;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLock {

    private volatile AtomicInteger ticket = new AtomicInteger(0);
    private volatile AtomicInteger flag = new AtomicInteger(0);

    void lock() {
        int myTicket = ticket.getAndIncrement();   //发号必须是一个原子操作，不能多个线程拿到同一个ticket
        while (myTicket != flag.get()) {
        }
    }

    void unlock() {
        flag.getAndIncrement();
    }

}

// 监听共享变量，开销大