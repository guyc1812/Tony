package com.avengers.Stark.JavaBasic.collection.list.arrayList.code;

import com.avengers.Stark.JavaBasic.time.Timer;

import java.util.ArrayList;
import java.util.Iterator;


public class Traversal {

    private static ArrayList getArrayList(int num) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) arrayList.add(i);
        return arrayList;
    }

    /* Traversal By Iterator */
    private static void traversalByIterator(ArrayList<Integer> list) {
        if (list == null) return;
        Timer.start();
        Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
        }
        Timer.end();
    }

    /* Traversal By ForEach */
    private static void traversalByForEach(ArrayList<Integer> list) {
        if (list == null) return;
        Timer.start();
        for (Integer item : list) {
            int i = item;
        }
        Timer.end();
    }

    /* Traversal By ForLoop */
    private static void traversalByForLoop(ArrayList<Integer> list) {
        if (list == null) return;
        Timer.start();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int j = list.get(i);
        }
        Timer.end();
    }

    /* Traversal By Stream */
    private static void traversalByStream(ArrayList<Integer> list) {
        if (list == null) return;
        ArrayList<Integer> n = new ArrayList(1000000);
        Timer.start();
        list.parallelStream().forEach(item -> {
            int i = item;
        });
        Timer.end();
    }

    public static void main(String[] args) {

        ArrayList<Integer> t1 = getArrayList(1000000);
        ArrayList<Integer> t2 = getArrayList(1000000);
        ArrayList<Integer> t3 = getArrayList(1000000);
        ArrayList<Integer> t4 = getArrayList(1000000);

        traversalByIterator(t1);  //Timer : 11 ms
        traversalByForEach(t2);   //Timer : 10 ms
        traversalByForLoop(t3);   //Timer : 9 ms
        traversalByStream(t4);    //Timer : 698 ms

    }
}
