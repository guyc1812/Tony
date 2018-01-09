package com.avengers.tony.JavaBasic.collection.set.treeSet.code;

import com.avengers.tony.JavaBasic.comparator.code.Avenger;
import com.avengers.tony.JavaBasic.comparator.code.AvengerNext;
import com.avengers.tony.JavaBasic.comparator.code.ComparatorUsage;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by yucgu on 2018/1/9.
 */
public class TreeSetConvert {

    public static void main(String[] args){

        Avenger IronMan = new Avenger("Tony", 40);
        Avenger Hulk = new Avenger("Banner", 44);
        Avenger Captain = new Avenger("Steve", 100);
        Avenger BlackWidow = new Avenger("Natasha", 30);
        List<Avenger> avengers = Lists.newArrayList(IronMan, Hulk, Captain, BlackWidow);

//        TreeSet<Avenger> as = new TreeSet<>(avengers);  // ==>Exception:Avenger cannot be cast to java.lang.Comparable

        AvengerNext IronManN = new AvengerNext("Tony", 40);
        AvengerNext HulkN = new AvengerNext("Banner", 44);
        AvengerNext CaptainN = new AvengerNext("Steve", 100);
        AvengerNext BlackWidowN = new AvengerNext("Natasha", 30);


        List<AvengerNext> avengersN = Lists.newArrayList(IronManN, HulkN, CaptainN, BlackWidowN);

        TreeSet<AvengerNext> as = new TreeSet<>(avengersN);
        System.out.println(as);

        //[Avenger(name=Natasha, age=30), Avenger(name=Tony, age=40), Avenger(name=Banner, age=44), Avenger(name=Steve, age=100)]

    }
}
