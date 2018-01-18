package com.avengers.tony.JavaBasic.collection.set.treeSet.code;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetUsage {

    public static void main(String[] args) {

        TreeSet tSet = new TreeSet();
        tSet.add("aaa");
        tSet.add("aaa");
        tSet.add("bbb");
        tSet.add("eee");
        tSet.add("ddd");
        tSet.add("ccc");

        // asc TreeSet
        ascIteratorThroughIterator(tSet);
        // desc TreeSet
        descIteratorThroughIterator(tSet);
        // for-each
        foreachTreeSet(tSet);

        System.out.printf("size : %d\n", tSet.size());

        // floor(<=)
        System.out.printf("floor bbb: %s\n", tSet.floor("bbb"));
        // lower(<)
        System.out.printf("lower bbb: %s\n", tSet.lower("bbb"));
        // ceiling(>=)
        System.out.printf("ceiling bbb: %s\n", tSet.ceiling("bbb"));
        System.out.printf("ceiling eee: %s\n", tSet.ceiling("eee"));
        // ceiling(>)
        System.out.printf("higher bbb: %s\n", tSet.higher("bbb"));
        // subSet()
        System.out.printf("subSet(aaa, true, ccc, true): %s\n", tSet.subSet("aaa", true, "ccc", true));
        System.out.printf("subSet(aaa, true, ccc, false): %s\n", tSet.subSet("aaa", true, "ccc", false));
        System.out.printf("subSet(aaa, false, ccc, true): %s\n", tSet.subSet("aaa", false, "ccc", true));
        System.out.printf("subSet(aaa, false, ccc, false): %s\n", tSet.subSet("aaa", false, "ccc", false));
        // headSet()
        System.out.printf("headSet(ccc, true): %s\n", tSet.headSet("ccc", true));
        System.out.printf("headSet(ccc, false): %s\n", tSet.headSet("ccc", false));
        // tailSet()
        System.out.printf("tailSet(ccc, true): %s\n", tSet.tailSet("ccc", true));
        System.out.printf("tailSet(ccc, false): %s\n", tSet.tailSet("ccc", false));


        // remove
        tSet.remove("ccc");

        // to array
        String[] arr = (String[]) tSet.toArray(new String[0]);
        for (String str : arr)
            System.out.printf("for each : %s\n", str);

        System.out.printf("TreeSet:%s\n", tSet);

        // Iterator
        for (Iterator iter = tSet.iterator(); iter.hasNext(); ) {
            System.out.printf("iter : %s\n", iter.next());
        }

        // pollFirst
        String val1 = (String) tSet.pollFirst();
        System.out.printf("pollFirst=%s, set=%s\n", val1, tSet);

        // pollLast
        String valn = (String) tSet.pollLast();
        System.out.printf("pollLast=%s, set=%s\n", valn, tSet);

        tSet.clear();

        System.out.printf("%s\n", tSet.isEmpty() ? "set is empty" : "set is not empty");
    }

    // asc
    public static void ascIteratorThroughIterator(TreeSet set) {
        System.out.print("\n ---- Ascend Iterator ----\n");
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            System.out.printf("asc : %s\n", iter.next());
        }
    }

    // desc
    public static void descIteratorThroughIterator(TreeSet set) {
        System.out.printf("\n ---- Descend Iterator ----\n");
        for (Iterator iter = set.descendingIterator(); iter.hasNext(); )
            System.out.printf("desc : %s\n", (String) iter.next());
    }

    // for-each
    private static void foreachTreeSet(TreeSet set) {
        System.out.printf("\n ---- For-each ----\n");
        String[] arr = (String[]) set.toArray(new String[0]);
        for (String str : arr)
            System.out.printf("for each : %s\n", str);
    }
}
