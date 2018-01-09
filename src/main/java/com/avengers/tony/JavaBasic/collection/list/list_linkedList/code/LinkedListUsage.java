package com.avengers.tony.JavaBasic.collection.list.list_linkedList.code;

import java.util.LinkedList;

public class LinkedListUsage {


    private static void basicApi() {

        LinkedList linkedList = new LinkedList();

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        // add "9" at index=0
        linkedList.add(0, "9");         // [9, 1, 2, 3]

        // set "0" at index=0 => avoid this
        linkedList.set(0, "0");         // [0, 1, 2, 3]

        // First -> add, remove, get -> ifFailed: Exception
        linkedList.addFirst("10");      // return void, [10, 0, 1, 2, 3]
        linkedList.removeFirst();       // return 10, [0, 1, 2, 3]
        linkedList.getFirst();          // return 0, [0, 1, 2, 3]

        // First -> offer, poll, peek -> ifFailed: null
        linkedList.offerFirst("10");    // return true, [10, 0, 1, 2, 3]
        linkedList.pollFirst();         // return 10, [0, 1, 2, 3]
        linkedList.peekFirst();         // return 0, [0, 1, 2, 3]

        // Last -> add, remove, get -> ifFailed: Exception
        linkedList.addLast("20");       // return void, [0, 1, 2, 3, 20]
        linkedList.removeLast();        // return 20, [0, 1, 2, 3]
        linkedList.getLast();           // return 3, [0, 1, 2, 3]

        // Last -> offer, poll, peek -> ifFailed: null
        linkedList.offerLast("20");     // return true, [0, 1, 2, 3, 20]
        linkedList.pollLast();          // return 20, [0, 1, 2, 3]
        linkedList.peekLast();          // return 3, [0, 1, 2, 3]

        // toArray(T[] a)
        String[] arr = (String[]) linkedList.toArray(new String[linkedList.size()]);
        // [0, 1, 2, 3]

        linkedList.clear();
    }


    private static void stackApi() {

        LinkedList stack = new LinkedList();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        stack.push("4");    // return void, [4, 3, 2, 1]
        stack.pop();        // return 4, [3, 2, 1]
        stack.peek();       // return 3, [3, 2, 1]

    }

    private static void queueApi() {

        LinkedList queue = new LinkedList();

        queue.add("1");
        queue.add("2");
        queue.add("3");

        queue.add("4");    // return true, [1, 2, 3, 4]
        queue.remove();     // return 1, [2, 3, 4]
        queue.element();    // return 2, [2, 3, 4]

    }

    public static void main(String[] args) {
        basicApi();
        stackApi();
        queueApi();
    }


}
