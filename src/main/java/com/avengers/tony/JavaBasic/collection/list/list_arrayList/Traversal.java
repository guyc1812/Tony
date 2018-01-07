package com.avengers.tony.JavaBasic.collection.list.list_arrayList;

import java.util.ArrayList;
import java.util.Iterator;

public class Traversal {

    private static ArrayList getLinkedList(int num) {
        ArrayList linkedList = new ArrayList();
        for (int i = 0; i < num; i++) linkedList.add(i);
        return linkedList;
    }

    /* 通过快迭代器遍历ArrayList */
    private static void traversalByIterator(ArrayList<Integer> list) {
        if (list == null) return;
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
        }
        long end = System.currentTimeMillis();
        System.out.println("Traversal By Iterator：" + (end - start) + " ms");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /* 通过ForEach来遍历ArrayList */
    private static void traversalByForEach(ArrayList<Integer> list) {
        if (list == null) return;
        long start = System.currentTimeMillis();
        for (Integer item : list) {
            int i = item;
        }
        long end = System.currentTimeMillis();
        System.out.println("Traversal By ForEach：" + (end - start) + " ms");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /* 通过快速随机访问遍历ArrayList */
    private static void traversalByFor(ArrayList<Integer> list) {
        if (list == null) return;
        long start = System.currentTimeMillis();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int j = list.get(i);
//            if(i==10){
//                list.remove(10);
//            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Traversal By For：" + (end - start) + " ms");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* 通过ForEach来遍历ArrayList */
    private static void traversalByStream(ArrayList<Integer> list) {
        if (list == null) return;

        ArrayList<Integer> n = new ArrayList(1000000);

        long start = System.currentTimeMillis();

        list.parallelStream().forEach(item -> {
            int i = item;
            n.add(item);
        });

        long end = System.currentTimeMillis();
        System.out.println("Traversal By Stream：" + (end - start) + " ms");
        System.out.println(n.get(10));

    }


    public static void main(String[] args) {

//        ArrayList<Integer> t1 = getLinkedList(1000000);
//        ArrayList<Integer> t2 = getLinkedList(1000000);
//        ArrayList<Integer> t3 = getLinkedList(1000000);
//        ArrayList<Integer> t4 = getLinkedList(1000000);
//        ArrayList<Integer> t5 = getLinkedList(1000000);
//        ArrayList<Integer> t6 = getLinkedList(1000000);
        ArrayList<Integer> t7 = getLinkedList(10000000);
        ArrayList<Integer> t8 = getLinkedList(10000000);

//        traversalByIterator(t1);
//        traversalByForEach(t2);
//        traversalByFor(t3);
//        traversalByStream(t4);
//
//        long start1 = System.currentTimeMillis();
//        Integer[] arr1 = t5.toArray(new Integer[0]);
//        long end1 = System.currentTimeMillis();
//        System.out.println("toArray By Size 0：" + (end1-start1) + " ms");
//        System.out.println(arr1.length);
//
//
//        long start2 = System.currentTimeMillis();
//        Integer[] arr2 = t6.toArray(new Integer[1000000]);
//        long end2 = System.currentTimeMillis();
//        System.out.println("toArray By Size n：" + (end2-start2) + " ms");
//        System.out.println(arr2.length);

//        long start3 = System.currentTimeMillis();
//        Integer[] arr3 = t.toArray(new Integer[0]);
//        long end3 = System.currentTimeMillis();
//        System.out.println("toArray By stream：" + (end3-start3) + " ms");

        ArrayList<Integer> n1 = new ArrayList(1000000);
        ArrayList<Integer> n2 = new ArrayList(1000000);

        long start4 = System.currentTimeMillis();
        t7.stream()
                .filter(p -> p % 2 != 0)
                .forEach(n1::add);
        long end4 = System.currentTimeMillis();
        System.out.println((end4 - start4) + " ms");


        long start5 = System.currentTimeMillis();
        for (Integer p : t8) {
            if (p % 2 != 0) {
                n2.add(p);
            }
        }
        long end5 = System.currentTimeMillis();
        System.out.println((end5 - start5) + " ms");


    }
}
