package com.avengers.Stark.JavaBasic.lock.code.MCS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * https://www.ibm.com/developerworks/cn/linux/l-cn-mcsspinlock/
 */
@Slf4j
public class MCSLock implements Lock {

    private AtomicReference<MCSNode> tail;
    private ThreadLocal<MCSNode> current;

    MCSLock() {
        this.tail = new AtomicReference<>();
        this.current = new ThreadLocal<>();
    }

    @Override
    public void lock() {
        // 1. 初始化当前节点 B
        MCSNode curNode = current.get();
        if (curNode == null) {
            curNode = new MCSNode(Thread.currentThread().getName());
            current.set(curNode);
        }
        // 2. 申请者 B 使用原子交换操作将 tail 指向自己以确定在链表中的位置，
        //    并返回 tail 原来的值 preNode.
        //    即使多个执行线程同时申请锁，由于交换操作的原子性，每个执行线程的申请顺序将会被唯一确定，不会出现不一致的现象。
        MCSNode preNode = tail.getAndSet(curNode);
        // 3. 如果 preNode 为 NULL，表明锁无人使用，B 立即成为锁的拥有者，申请过程结束。
        if (preNode != null) {
            // 4. 如果 preNode 不为 NULL，则表明此时 tail 指向申请者 B 的直接前驱 A, 因此必须将 A 的 next 域指向 B 自己，从而将链表构建完整。
            preNode.next = curNode;
            // 5. 然后 B 一直在自己的 locked 域上自旋，直到其前驱节点将自身的 locked 设置为 true
            while (!curNode.locked) {
                System.out.println(String.format(
                        "Thread %s is waiting, pre is %s",
                        Thread.currentThread().getName(),
                        preNode.getName()));
            }
        }
    }

    @Override
    public void unlock() {
        MCSNode current = this.current.get();
        this.current.remove();
        // 1. 如果当前节点有直接后继，即 next 域不为 NULL，那么只须将后继节点的 locked 域置为 1 即可
        if (current.getNext() != null) {
            current.getNext().setLocked(true);
        }
        // 2. 如果当前节点是最后一个申请者
        if (tail.compareAndSet(current, null)) {
            return;
        } else {
            // 3. 如果当前节点不是最后一个申请者，说明中途来了新的申请者，那么需要一直等待新的申请者将链表构建完整
            while (current.getNext() == null) {
                // waiting for B complete the list
            }
        }
        // 4. 此时当前节点不是最后一个申请者，并且存在后续节点，那么将后续节点 locked 域置为 1
        current.getNext().setLocked(true);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
