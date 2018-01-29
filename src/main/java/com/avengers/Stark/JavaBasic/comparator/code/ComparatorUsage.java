package com.avengers.Stark.JavaBasic.comparator.code;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

@Service("myComparator")
public class ComparatorUsage {

    /**
     * JAVA 7
     * Comparator<Avenger> ComparatorDemo = new Comparator<Avenger>(){
     * public int compare(UTRSuite a1, UTRSuite a2) {
     * return a1.getName().compareTo(a2.getName());
     * }
     * };
     * <p>
     * JAVA 8
     * Comparator<Avenger> comparatorByName = Comparator.comparing(Avenger::getName);
     */

    public List<Avenger> AvengersByName(List<Avenger> avengers) {

        Comparator<Avenger> comparatorByName = Comparator.comparing(Avenger::getName);
        avengers.sort(comparatorByName);
        return avengers;

    }

    public List<Avenger> AvengersByNameReversed(List<Avenger> avengers) {

        Comparator<Avenger> comparatorByName = Comparator.comparing(Avenger::getName).reversed();
        avengers.sort(comparatorByName);
        return avengers;

    }

    public List<Avenger> AvengersByAge(List<Avenger> avengers) {

        Comparator<Avenger> comparatorByAge = Comparator.comparing(Avenger::getAge);
        avengers.sort(comparatorByAge);
        return avengers;

    }

    public List<Avenger> AvengersByAgeReversed(List<Avenger> avengers) {

        Comparator<Avenger> comparatorByAge = Comparator.comparing(Avenger::getAge).reversed();
        avengers.sort(comparatorByAge);
        return avengers;

    }

    public static void main(String[] args) {

        // usage in treeSet
        TreeSet<Avenger> treeSet = new TreeSet<>(Comparator.comparing(Avenger::getName));
        // usage in treeMap
        TreeMap<Avenger, String> treeMap = new TreeMap<>(Comparator.comparing(Avenger::getName));

        Avenger IronMan = new Avenger("Tony", 40);
        Avenger Hulk = new Avenger("Banner", 44);
        Avenger Captain = new Avenger("Steve", 100);
        Avenger BlackWidow = new Avenger("Natasha", 30);
        List<Avenger> avengers = Lists.newArrayList(IronMan, Hulk, Captain, BlackWidow);

        List<Avenger> avengersByName = new ComparatorUsage().AvengersByName(avengers);
        System.out.println(avengersByName);

        List<Avenger> avengersByAge = new ComparatorUsage().AvengersByAgeReversed(avengers);
        System.out.println(avengersByAge);

    }

}
