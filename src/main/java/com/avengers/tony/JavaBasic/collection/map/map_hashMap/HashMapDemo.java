package com.avengers.core.demo.JavaBasic.collection.map.map_hashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*****
 * Created by apple on 2017/11/26.
 */
public class HashMapDemo {

    public static void main(String[] args) {

        HashMap<String,Integer> map = new HashMap();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("map:"+map );

        // traversal-1
        Iterator iter1 = map.entrySet().iterator();
        while(iter1.hasNext()) {
            Map.Entry entry = (Map.Entry)iter1.next();
            String k = (String)entry.getKey();
            int v = (Integer)entry.getValue();
        }

        // traversal-2
        Iterator iter2 = map.keySet().iterator();
        while (iter2.hasNext()) {
            String k = (String)iter2.next();
            int v = map.get(k);
        }

        // traversal-3
        Collection c = map.values();
        Iterator iter3= c.iterator();
        while (iter3.hasNext()) {
            int v = (Integer)iter3.next();
        }

        // traversal-4
        map.forEach((k,v)->{
            String key = k;
            int value = v;
        });

        // HashMap的键值对个数
        System.out.println("size:"+map.size());

        // containsKey(Object key) :是否包含键key
        System.out.println("contains key two : "+map.containsKey("two"));
        System.out.println("contains key five : "+map.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.println("contains value 0 : "+map.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
        map.remove("three");

        System.out.println("map:"+map );

        // clear() ： 清空HashMap
        map.clear();

        // isEmpty() : HashMap是否为空
        System.out.println((map.isEmpty()?"map is empty":"map is not empty") );


    }
}
