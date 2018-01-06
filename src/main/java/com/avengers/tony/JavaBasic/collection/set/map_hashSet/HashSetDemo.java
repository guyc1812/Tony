package com.avengers.core.demo.JavaBasic.collection.set.map_hashSet;

import java.util.*;

/*****
 * Created by apple on 2017/11/26.
 */
public class HashSetDemo {

    public static void main(String[] args) {

        HashSet<String> set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");

        // traversal -1 通过Iterator遍历HashSet。推荐方式
        for(Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            System.out.printf("iterator : %s\n", iterator.next());
        }

        // traversal -2 通过for-each遍历HashSet。不推荐！此方法需要先将Set转换为数组
        String[] arr = (String[])set.toArray(new String[0]);
        for (String str:arr){
            System.out.printf("for each : %s\n", str);
        }

        // 打印HashSet的实际大小
        System.out.printf("size : %d\n", set.size());

        // 判断HashSet是否包含某个值
        System.out.printf("HashSet contains a :%s\n", set.contains("a"));
        System.out.printf("HashSet contains g :%s\n", set.contains("g"));

        // 删除HashSet中的“e”
        set.remove("e");

        // 新建一个包含b、c、f的HashSet
        HashSet otherset = new HashSet();
        otherset.add("b");
        otherset.add("c");
        otherset.add("f");

        // 克隆一个removeset，内容和set一模一样
        HashSet removeset = (HashSet)set.clone();

        // 删除“removeset中，属于otherSet的元素”
        removeset.removeAll(otherset);

        // 打印removeset
        System.out.printf("removeset : %s\n", removeset);

        // 克隆一个retainset，内容和set一模一样
        HashSet retainset = (HashSet)set.clone();

        // 保留“retainset中，属于otherSet的元素”
        retainset.retainAll(otherset);

        // 打印retainset
        System.out.printf("retainset : %s\n", retainset);

        // 清空HashSet
        set.clear();

        // 输出HashSet是否为空
        System.out.printf("%s\n", set.isEmpty()?"set is empty":"set is not empty");

    }


}
