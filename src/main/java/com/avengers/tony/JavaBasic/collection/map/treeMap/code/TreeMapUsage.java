package com.avengers.tony.JavaBasic.collection.map.treeMap.code;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapUsage {

    public static void main(String[] args) {

        basicApi();
        navigableApi();
        subMapApi();

    }


    // basicApi
    private static void basicApi() {

        TreeMap<String, Integer> tmap = new TreeMap<>();

        tmap.put("one", 1);
        tmap.put("two", 2);
        tmap.put("three", 3);       // {one=1, three=3, two=2}

        tmap.size();                // 3

        tmap.containsKey("two");    // true
        tmap.containsKey("five");   // false

        tmap.containsValue(new Integer(0));     // false

        tmap.remove("three");       // return 3, {one=1, two=2}

        tmap.clear();

        tmap.isEmpty();             // true

    }


    // subMapApi
    public static void subMapApi() {

        TreeMap tmap = new TreeMap();

        tmap.put("a", 101);
        tmap.put("b", 102);
        tmap.put("c", 103);
        tmap.put("d", 104);
        tmap.put("e", 105);

        // {a=101, b=102, c=103, d=104, e=105}

        tmap.headMap("c");                  // {a=101, b=102}
        tmap.headMap("c", true);            // {a=101, b=102, c=103}
        tmap.headMap("c", false);           // {a=101, b=102}

        tmap.tailMap("c");                  // {c=103, d=104, e=105}
        tmap.tailMap("c", true);            // {c=103, d=104, e=105}
        tmap.tailMap("c", false);           // {d=104, e=105}

        tmap.subMap("a", "c");              // {a=101, b=102}
        tmap.subMap("a", true, "c", true);  // {a=101, b=102, c=103}
        tmap.subMap("a", true, "c", false); // {a=101, b=102}
        tmap.subMap("a", false, "c", true); // {b=102, c=103}
        tmap.subMap("a", false, "c", false);// {b=102}

        tmap.navigableKeySet();             // [a, b, c, d, e]

        tmap.descendingKeySet();            // [e, d, c, b, a]

    }


    // navigableApi
    public static void navigableApi() {

        NavigableMap nav = new TreeMap();

        nav.put("aaa", 111);
        nav.put("bbb", 222);
        nav.put("eee", 333);
        nav.put("ccc", 555);
        nav.put("ddd", 444);

        // {aaa=111, bbb=222, ccc=555, ddd=444, eee=333} with order

        nav.firstKey();         // aaa or NoSuchElementException
        nav.firstEntry();       // aaa=111 or NoSuchElementException

        nav.lastKey();          // eee or NoSuchElementException
        nav.lastEntry();        // eee=333 or NoSuchElementException

        nav.floorKey("bbb");    // bbb or null
        nav.lowerKey("bbb");    // aaa or null

        nav.ceilingKey("ccc");  // ccc or null
        nav.higherKey("ccc");   // ddd or null
    }

}