package com.avengers.tony.JavaBasic.atomic.code;

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

}
