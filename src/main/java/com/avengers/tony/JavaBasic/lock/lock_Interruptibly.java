package com.avengers.tony.JavaBasic.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lock_Interruptibly extends Thread {

    Lock lock;

    lock_Interruptibly(Lock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }finally {
            if(lock.tryLock()){ //if hold a lock
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }
        }
    }

    public static void main(String[] args)  {

        Lock lock = new ReentrantLock();

        lock_Interruptibly thread1 = new lock_Interruptibly(lock);
        lock_Interruptibly thread2 = new lock_Interruptibly(lock);
        try {
            thread1.start();
            Thread.sleep(2000);
            thread2.start();
            Thread.sleep(4000);
            thread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
