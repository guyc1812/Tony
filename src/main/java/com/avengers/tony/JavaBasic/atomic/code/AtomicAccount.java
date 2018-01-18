package com.avengers.tony.JavaBasic.atomic.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicAccount {

    private AtomicLong balance;

    public AtomicAccount(long money) {
        balance = new AtomicLong(money);
        System.out.println("Total Money:" + balance);
    }

    public void deposit(long money) {
        balance.addAndGet(money);
    }

    public void withdraw(long money) {
        // every thread can withdraw successfully with while (true)
        // only one thread will withdraw successfully when removing while (true)
        while (true) {
            long oldValue = balance.get();
            if (oldValue < money) {
                System.out.println(Thread.currentThread().getName() + " Insufficient! balance: " + balance);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (Exception ignored) {
            }
            if (balance.compareAndSet(oldValue, oldValue - money)) {
                System.out.println(Thread.currentThread().getName() + " Withdraw " + money + " successfully!  balance: " + balance);
                break;
            }
            System.out.println(Thread.currentThread().getName() + " Blocked, try again");
        }
    }

    public static void main(String[] args) {
        final AtomicAccount account = new AtomicAccount(900);
        ExecutorService service = Executors.newFixedThreadPool(9);
        for (int i = 1; i < 10; i++) {
            service.execute(() -> account.withdraw(100));
        }
        service.shutdown();
    }

}


