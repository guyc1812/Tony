package com.avengers.core.demo.JavaBasic.collection.list.list_linkedList;

import java.util.Iterator;
import java.util.LinkedList;

/*****
 * Created by apple on 2017/11/26.
 */
public class Traversal {

    private static LinkedList getLinkedList(int num) {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < num; i++) linkedList.addLast(i);
        return linkedList;
    }

    /* 通过快迭代器遍历LinkedList */
    private static void traversalByIterator(LinkedList<Integer> list) {
        if (list == null) return;
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
        }
        long end = System.currentTimeMillis();
        System.out.println("Traversal By Iterator：" + (end-start) + " ms");
    }


    /* 通过ForEach来遍历LinkedList */
    private static void traversalByForEach(LinkedList<Integer> list) {
        if (list == null) return;
        long start = System.currentTimeMillis();
        for (Integer item : list){
            int i = item;
        }
        long end = System.currentTimeMillis();
        System.out.println("Traversal By ForEach：" + (end-start) + " ms");
    }


    /* 通过快速随机访问遍历LinkedList */
    private static void traversalByFor(LinkedList<Integer> list) {
        if (list == null) return;
        long start = System.currentTimeMillis();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Traversal By For：" + (end-start) + " ms");
    }


    public static void main(String[] args) {

        traversalByIterator(getLinkedList(100000));
        traversalByForEach(getLinkedList(100000));
        traversalByFor(getLinkedList(100000));

    }
}
