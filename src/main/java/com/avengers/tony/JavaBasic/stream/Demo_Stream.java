package com.avengers.core.demo.JavaBasic.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/*****
 * Created by apple on 2017/12/24.
 */
public class Demo_Stream {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList(
                "bcd", "cde", "def", "abc");
        List<String> result = list.stream()
                .parallel()
                .filter(e -> e.length() >= 3)
                .map(e -> e.charAt(0))
                //.peek(System.out :: println)
                //.sorted()
                //.peek(e -> System.out.println("++++" + e))
                .map(e -> String.valueOf(e))
                .collect(Collectors.toList());
        System.out.println("----------------------------");
        System.out.println(result);
    }
}
