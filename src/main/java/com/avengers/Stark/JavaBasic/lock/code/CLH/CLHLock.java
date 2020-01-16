package com.avengers.Stark.JavaBasic.lock.code.CLH;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class CLHLock implements Lock {

    private AtomicReference<CLHNode> tail;
    private ThreadLocal<CLHNode> current;

    CLHLock() {
        this.tail = new AtomicReference<>();
        this.current = new ThreadLocal<>();
    }

    @Override
    public void lock() {
        // 1. 初始化当前节点 B
        CLHNode curNode = current.get();
        if (curNode == null) {
            curNode = new CLHNode(Thread.currentThread().getName());
            current.set(curNode);
        }
        // 2. 申请者 B 使用原子交换操作将 tail 指向自己以确定在链表中的位置，
        //    并返回 tail 原来的值 preNode.
        //    即使多个执行线程同时申请锁，由于交换操作的原子性，每个执行线程的申请顺序将会被唯一确定，不会出现不一致的现象。
        CLHNode predNode = tail.getAndSet(curNode);
        // 3. 如果 preNode 为 NULL，表明锁无人使用，B 立即成为锁的拥有者，申请过程结束。
        if (predNode != null) {
            // 4. 如果 preNode 不为 NULL，则基于前驱节点的锁值（locked==true）进行自旋，直到前驱节点的锁值 locked == false。
            while (predNode.isLocked()) {
                System.out.println(String.format(
                        "Thread %s is waiting, pre is %s",
                        Thread.currentThread().getName(),
                        predNode.getName()));
            }
        }
    }

    @Override
    public void unlock() {
        CLHNode curNode = current.get();
        current.remove();
        // 1. 获取当前线程的锁节点，如果节点为空或者锁值（locked== false）则无需解锁，直接返回。
        if (curNode == null || !curNode.isLocked())
            return;
        // 2. 使用同步方法为尾节点赋空值，赋值不成功则表示当前节点不是尾节点，
        //    需要将当前节点的 locked == false 已保证解锁该节点，并让后续节点跳出自旋
        if (!tail.compareAndSet(curNode, null))
            curNode.setLocked(false);
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

// MCS：John Mellor-Crummey and Michael Scott
// CLH：Craig，Landin andHagersten

// SMP(Symmetric Multi-Processor)
// 对称多处理器结构，它是相对非对称多处理技术而言的、应用十分广泛的并行技术。
// 在这种架构中，一台计算机由多个CPU组成，并共享内存和其他资源，所有的CPU都可以平等地访问内存、I/O和外部中断。
// 虽然同时使用多个CPU，但是从管理的角度来看，它们的表现就像一台单机一样。
// 操作系统将任务队列对称地分布于多个CPU之上，从而极大地提高了整个系统的数据处理能力。
// 但是随着CPU数量的增加，每个CPU都要访问相同的内存资源，共享资源可能会成为系统瓶颈，导致CPU资源浪费。

// NUMA(Non-Uniform Memory Access)
// 非一致存储访问，将CPU分为CPU模块，每个CPU模块由多个CPU组成，并且具有独立的本地内存、I/O槽口等，模块之间可以通过互联模块相互访问。
// 访问本地内存（本CPU模块的内存）的速度将远远高于访问远地内存(其他CPU模块的内存)的速度，这也是非一致存储访问的由来。
// NUMA较好地解决SMP的扩展问题，当CPU数量增加时，因为访问远地内存的延时远远超过本地内存，系统性能无法线性增加。

// AQS AbstractQueuedSynchronizer，即队列同步器
// https://www.jianshu.com/p/d3ec3018292d