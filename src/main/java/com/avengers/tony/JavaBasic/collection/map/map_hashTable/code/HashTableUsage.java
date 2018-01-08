package com.avengers.tony.JavaBasic.collection.map.map_hashTable;

import java.util.*;

public class HashtableDemo {

    public static void main(String[] args) {

        Hashtable<String,Integer> table = new Hashtable();

        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        System.out.println("table:"+table );

        // traversal-1
        Iterator iter1 = table.entrySet().iterator();
        while(iter1.hasNext()) {
            Map.Entry entry = (Map.Entry)iter1.next();
            String k = (String)entry.getKey();
            int v = (Integer)entry.getValue();
        }

        // traversal-2
        Iterator iter2 = table.keySet().iterator();
        while (iter2.hasNext()) {
            String k = (String)iter2.next();
            int v = table.get(k);
        }

        // traversal-3
        Collection c = table.values();
        Iterator iter3= c.iterator();
        while (iter3.hasNext()) {
            int v = (Integer)iter3.next();
        }

        // traversal-4
        table.forEach((k,v)->{
            String key = k;
            int value = v;
        });

        // traversal-5
        Enumeration enu1 = table.keys();     //->keys
        while(enu1.hasMoreElements()) {
            System.out.println(enu1.nextElement());
        }

        // traversal-6
        Enumeration enu2 = table.elements(); //->values
        while(enu2.hasMoreElements()) {
            System.out.println(enu2.nextElement());
        }

        // Hashtable的键值对个数
        System.out.println("size:"+table.size());

        // containsKey(Object key) :是否包含键key
        System.out.println("contains key two : "+table.containsKey("two"));
        System.out.println("contains key five : "+table.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.println("contains value 0 : "+table.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
        table.remove("three");

        System.out.println("table:"+table );

        // clear() ： 清空Hashtable
        table.clear();

        // isEmpty() : Hashtable是否为空
        System.out.println((table.isEmpty()?"table is empty":"table is not empty") );


    }
}
