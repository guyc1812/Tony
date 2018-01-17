package com.avengers.tony.JavaBasic.atomic.code;

import java.util.Random;
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
        while (true) {//保证即时同一时间有人也在取款也可以再次尝试取款，如果不需要并发尝试取款，可以去掉这句
            long oldValue = balance.get();
            if (oldValue < money) {
                System.out.println(Thread.currentThread().getName() + " 余额不足！ 余额：" + balance);
                break;
//                return;
            }
            try {Thread.sleep(500);} catch (Exception ignored) { }// 模拟取款时间
            if (balance.compareAndSet(oldValue, oldValue - money)) {
                System.out.println(Thread.currentThread().getName() + " 取款 " + money + " 成功！ 余额：" + balance);
                break;
//                return;
            }
            System.out.println(Thread.currentThread().getName() + " 遇到并发，再次尝试取款！");
        }
    }

    public static void main(String[] args) {
        final AtomicAccount account = new AtomicAccount(3000);
        ExecutorService service = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 30; i++) {
            service.execute(() -> account.withdraw(100));
        }
        service.shutdown();
    }

}
