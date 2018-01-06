package com.avengers.core.demo.JavaBasic.collection.list.list_arrayList;

import java.util.*;

/*****
 * Created by apple on 2017/11/26.
 */
public class ListDemo {

    public static void main(String[] args) {


        Set<String> set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");

        ArrayList<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        HashMap<String,String> map = new HashMap();

        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");

        list.addAll(set);
        list.addAll(map.keySet());


        // 删除“3”
        list.remove("3");


        // 判断list中是否包含"3"
        System.out.println("ArrayList contains 3 is: "+ list.contains(3));

        // 设置第2个元素为10
        list.set(1, "10");

        // 通过Iterator遍历ArrayList
        for(Iterator iter = list.iterator(); iter.hasNext(); ) {
            System.out.println("next is: "+ iter.next());
        }

        // 将ArrayList转换为数组
        String[] arr = (String[])list.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));

        // 清空ArrayList
        list.clear();

        // 判断ArrayList是否为空
        System.out.println("ArrayList is empty: "+ list.isEmpty());
    }


}
