package com.avengers.tony.JavaBasic.comparator;

import com.avengers.core.BaseTest;
import com.avengers.core.demo.JavaBasic.comparator.Avenger;
import com.avengers.core.demo.JavaBasic.comparator.MyComparator;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComparatorDemoTest extends BaseTest {

    @Autowired
    MyComparator myComparator;

    Avenger IronMan = new Avenger("Tony", 40);
    Avenger Hulk = new Avenger("Banner", 44);
    Avenger Captain = new Avenger("Steve", 100);
    Avenger BlackWidow = new Avenger("Natasha", 30);

    List<Avenger> avengers = Lists.newArrayList(IronMan, Hulk, Captain, BlackWidow);

    @Test
    public void comparatorByName() {

        List<Avenger> avengersByName = myComparator.AvengersByName(avengers);
        System.out.println(avengersByName);

    }

    @Test
    public void AvengersByAgeReversed() {

        List<Avenger> avengersByAge = myComparator.AvengersByAgeReversed(avengers);
        System.out.println(avengersByAge);

    }

}
