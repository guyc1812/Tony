package com.avengers.Stark.JavaBasic.collection.map.hashTable.code;

import java.util.*;

public class HashTableUsage {

    public static void main(String[] args) {

        Hashtable<String, Integer> table = new Hashtable<>();

        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        System.out.println("table:" + table);

        // traversal of entrySet
        Iterator iter1 = table.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry entry = (Map.Entry) iter1.next();
            String k = (String) entry.getKey();
            int v = (Integer) entry.getValue();
        }

        // traversal of keySet
        Iterator iter2 = table.keySet().iterator();
        while (iter2.hasNext()) {
            String k = (String) iter2.next();
            int v = table.get(k);
        }

        // traversal of values
        Collection c = table.values();
        Iterator iter3 = c.iterator();
        while (iter3.hasNext()) {
            int v = (Integer) iter3.next();
        }

        // traversal of entries
        table.forEach((k, v) -> {
            String key = k;
            int value = v;
        });

        // traversal by Enumeration of keys
        Enumeration enu1 = table.keys();
        while (enu1.hasMoreElements()) {
            System.out.println(enu1.nextElement());
        }

        // traversal by Enumeration of values
        Enumeration enu2 = table.elements();
        while (enu2.hasMoreElements()) {
            System.out.println(enu2.nextElement());
        }

        // size()
        System.out.println("size:" + table.size());

        // containsKey(Object key)
        System.out.println("contains key two : " + table.containsKey("two"));
        System.out.println("contains key five : " + table.containsKey("five"));

        // containsValue(Object value)
        System.out.println("contains value 0 : " + table.containsValue(new Integer(0)));

        // remove(Object key)
        table.remove("three");

        System.out.println("table:" + table);

        // clear()
        table.clear();

        // isEmpty()
        System.out.println((table.isEmpty() ? "table is empty" : "table is not empty"));


    }
}
