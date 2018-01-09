package com.avengers.tony.JavaBasic.collection.list.list_arrayList.code;

import java.util.*;

public class ArrayListUsage {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Set<String> set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");

        HashMap<String,String> map = new HashMap();

        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");

        list.addAll(set);
        list.addAll(map.keySet());

        // Remove “3”
        list.remove("3");

        // If contains "3"
        System.out.println("ArrayList contains 3 is: "+ list.contains(3));

        // Set the 2nd element to 10
        list.set(1, "10");

        // Traversal by Iterator
        for(Iterator iter = list.iterator(); iter.hasNext(); ) {
            System.out.println("next is: "+ iter.next());
        }

        // ArrayList to array
        String[] arr = (String[])list.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));

        // Clear ArrayList
        list.clear();

        // If Empty
        System.out.println("ArrayList is empty: "+ list.isEmpty());
    }

}
