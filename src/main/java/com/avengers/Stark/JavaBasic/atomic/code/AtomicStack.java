package com.avengers.Stark.JavaBasic.atomic.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicStack<T> {

    private AtomicReference<Node<T>> stacks = new AtomicReference<Node<T>>();

    public T push(T e) {
        Node<T> oldNode, newNode;
        while (true) {
            oldNode = stacks.get();
            newNode = new Node<>(e, oldNode);
            if (stacks.compareAndSet(oldNode, newNode)) {
                return e;
            }
        }
    }

    public T pop() {
        Node<T> oldNode, newNode;
        while (true) {
            oldNode = stacks.get();
            newNode = oldNode.next;
            if (stacks.compareAndSet(oldNode, newNode)) {
                return oldNode.object;
            }
        }
    }

    private static final class Node<T> {
        private T object;

        private Node<T> next;

        private Node(T newNode, Node<T> oldNode) {
            this.object = newNode;
            this.next = oldNode;
        }
    }

    public static void main(String[] args){

        AtomicStack stack = new AtomicStack();
        ExecutorService service = Executors.newFixedThreadPool(6);

        service.execute(()->stack.push("a"));
        service.execute(()->stack.push("b"));
        service.execute(()->stack.push("c"));

        service.execute(()->System.out.println(Thread.currentThread().getName()+" "+stack.pop()));
        service.execute(()->System.out.println(Thread.currentThread().getName()+" "+stack.pop()));
        service.execute(()->System.out.println(Thread.currentThread().getName()+" "+stack.pop()));

    }

}
