package com.avengers.tony.JavaBasic.collection.list.list_linkedList.code;

import com.avengers.tony.JavaBasic.time.Timer;
import java.util.Iterator;
import java.util.LinkedList;

public class Traversal {

    private static LinkedList getLinkedList(int num) {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < num; i++) linkedList.addLast(i);
        return linkedList;
    }

    /* Traversal By Iterator */
    private static void traversalByIterator(LinkedList<Integer> list) {
        if (list == null) return;
        Timer.start();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
        }
        Timer.end();
    }

    /* Traversal By ForEach */
    private static void traversalByForEach(LinkedList<Integer> list) {
        if (list == null) return;
        Timer.start();
        for (Integer item : list){
            int i = item;
        }
        Timer.end();
    }

    /* Traversal By ForLoop => avoid this */
    private static void traversalByForLoop(LinkedList<Integer> list) {
        if (list == null) return;
        Timer.start();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        Timer.end();
    }

    public static void main(String[] args) {

        traversalByIterator(getLinkedList(100000));
            // Timer : 6 ms
        traversalByForEach(getLinkedList(100000));
            // Timer : 5 ms
        traversalByForLoop(getLinkedList(100000));
            // Timer : 3287 ms

    }
}
