package com.avengers.Stark.JavaBasic.comparator.code;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by yucgu on 2018/1/9.
 */
public class ComparableUsage {

    public static void main(String[] args) {

        AvengerNext IronMan = new AvengerNext("Tony", 40);
        AvengerNext Hulk = new AvengerNext("Banner", 44);
        AvengerNext Captain = new AvengerNext("Steve", 100);
        AvengerNext BlackWidow = new AvengerNext("Natasha", 30);

        List<AvengerNext> avengers = Lists.newArrayList(IronMan, Hulk, Captain, BlackWidow);

        Collections.sort(avengers);
        System.out.println(avengers);
    }

}
