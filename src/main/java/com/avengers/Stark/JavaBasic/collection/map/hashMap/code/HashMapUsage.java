package com.avengers.Stark.JavaBasic.collection.map.hashMap.code;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapUsage {

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("map:" + map);

        // traversal of entrySet
        Iterator iter1 = map.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry entry = (Map.Entry) iter1.next();
            String k = (String) entry.getKey();
            int v = (Integer) entry.getValue();
        }

        // traversal of keySet
        Iterator iter2 = map.keySet().iterator();
        while (iter2.hasNext()) {
            String k = (String) iter2.next();
            int v = map.get(k);
        }

        // traversal of values
        Collection c = map.values();
        Iterator iter3 = c.iterator();
        while (iter3.hasNext()) {
            int v = (Integer) iter3.next();
        }

        // traversal of entries
        map.forEach((k, v) -> {
            String key = k;
            int value = v;
        });

        // size()
        System.out.println("size:" + map.size());

        // containsKey(Object key)
        System.out.println("contains key two : " + map.containsKey("two"));
        System.out.println("contains key five : " + map.containsKey("five"));

        // containsValue(Object value)
        System.out.println("contains value 0 : " + map.containsValue(new Integer(0)));

        // remove(Object key)
        map.remove("three");

        System.out.println("map:" + map);

        // clear()
        map.clear();

        // isEmpty()
        System.out.println((map.isEmpty() ? "map is empty" : "map is not empty"));


    }
}
