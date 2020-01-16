package com.avengers.Stark.JavaBasic.lock.code.NaticeLock;

import java.util.concurrent.atomic.AtomicBoolean;

public class NativeLock {

    private volatile AtomicBoolean flag = new AtomicBoolean(false);

    void lock() {
        while (!flag.compareAndSet(false, true)) {
            //返回true：占锁成功，返回false：占锁失败，继续循环尝试
        }
    }

    void unlock() {
        flag.set(false);
    }

}

// 无法保证公平性