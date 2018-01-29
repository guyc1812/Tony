package com.avengers.Stark.JavaBasic.collection.set.hashSet.code;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo {

    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");

        // traversal -1
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            System.out.printf("iterator : %s\n", iterator.next());
        }

        // traversal -2
        String[] arr = (String[]) set.toArray(new String[0]);
        for (String str : arr) {
            System.out.printf("for each : %s\n", str);
        }

        System.out.printf("size : %d\n", set.size());

        // set.contains
        System.out.printf("HashSet contains a :%s\n", set.contains("a"));
        System.out.printf("HashSet contains g :%s\n", set.contains("g"));

        // set.remove
        set.remove("e");

        HashSet otherset = new HashSet();
        otherset.add("b");
        otherset.add("c");
        otherset.add("f");

        // set.clone()
        HashSet removeset = (HashSet) set.clone();

        // removeAll()
        removeset.removeAll(otherset);
        System.out.printf("removeset : %s\n", removeset);

        HashSet retainset = (HashSet) set.clone();

        // retainAll()
        retainset.retainAll(otherset);
        System.out.printf("retainset : %s\n", retainset);

        set.clear();
        System.out.printf("%s\n", set.isEmpty() ? "set is empty" : "set is not empty");

    }


}
