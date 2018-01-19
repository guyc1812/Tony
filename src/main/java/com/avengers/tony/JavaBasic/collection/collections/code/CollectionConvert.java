package com.avengers.tony.JavaBasic.collection.collections.code;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionConvert {


    public static void main(String[] args) {

        int[] intArray = new int[]{1, 2, 3, 4, 5};

        //box

        //1 - Integer.valueOf(int i)
        Integer[] IntArray1 = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            IntArray1[i] = Integer.valueOf(intArray[i]);
            // or even
            // IntArray1[i] = intArray[i];
        }

        //2 - Arrays.stream(intArray).boxed().toArray(Integer[]::new);
        Integer[] IntArray2 = Arrays.stream(intArray)
                .boxed()    // int -> Integer
                .toArray(Integer[]::new);


        //3 - import org.apache.commons.lang3.ArrayUtils;
        Integer[] IntArray3 = ArrayUtils.toObject(intArray);


        String[] StrArray = new String[]{"a", "b", "c"};

        List<String> arrayList = new ArrayList<>();
        arrayList.add("q");
        arrayList.add("w");
        arrayList.add("e");


        //array to list (ArrayList implicitly)
        List<String> list1 = Arrays.asList(StrArray);

        List<Integer> list2 = Arrays.stream(intArray)
                .boxed()            // int -> Integer
                .collect(Collectors.toList());


        //array to XxxList
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList(StrArray));
        LinkedList<String> list4 = new LinkedList<>(Arrays.asList(StrArray));

        LinkedList<String> list5 = new LinkedList<>();
        Collections.addAll(list5, StrArray);

        //array to set
        Set<String> set1 = new HashSet<>(Arrays.asList(StrArray));
        Set<Integer> set2 = Arrays.stream(intArray)
                .boxed()              // int -> Integer
                .collect(Collectors.toSet());

        Set<String> set3 = new HashSet<>();
        Collections.addAll(set3, StrArray);


        //list to array
        String[] array1 = list1.stream().toArray(String[]::new);
        String[] array2 = list1.toArray(new String[0]);
        String[] array3 = list1.toArray(new String[list1.size()]);  //more efficient


        //set to array
        String[] array4 = set1.stream().toArray(String[]::new);
        String[] array5 = set1.toArray(new String[0]);
        String[] array6 = set1.toArray(new String[set1.size()]);    //more efficient

        //list to set
        Set<String> set4 = new HashSet<>(list1);

        //set to list
        LinkedList<String> list6 = new LinkedList<>(set4);


        Map<String, Integer> map = new HashMap<>();

        //map to collection

        //values
        List<Integer> valueList = new ArrayList<>(map.values());
        Set<Integer> valueset = new HashSet<>(map.values());

        //keys
        List<String> keyList = new ArrayList<>(map.keySet());
        Set<Integer> keySet = new HashSet<>(map.values());

    }


}
