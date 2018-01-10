# Collection Conversion


### box

```
int[] intArray = new int[]{1, 2, 3, 4, 5};
```

1.Integer.valueOf(int i)

```
Integer[] IntArray = new Integer[intArray.length];

for (int i = 0; i < intArray.length; i++) {
    IntArray[i] = Integer.valueOf(intArray[i]);
    // IntArray[i] = intArray[i];  <= or even this
}
```

2.Arrays.stream(intArray).boxed().toArray(Integer[]::new);

```
Integer[] IntArray = Arrays.stream(intArray)
                           .boxed()                  // int -> Integer
                           .toArray(Integer[]::new);
```

3.import org.apache.commons.lang3.ArrayUtils;

```
Integer[] IntArray = ArrayUtils.toObject(intArray);
```


## Array to List

```
// Array to List<T> (ArrayList implicitly)

List<String>  list1 = Arrays.asList(StrArray);

List<Integer> list2 = Arrays.stream(intArray)
                            .boxed()                  // int -> Integer
                            .collect(Collectors.toList());

//array to XxxList

ArrayList<String>  list3 = new ArrayList<>(Arrays.asList(StrArray));
LinkedList<String> list4 = new LinkedList<>(Arrays.asList(StrArray));

LinkedList<String> list5 = new LinkedList<>();
Collections.addAll(list5, StrArray);
```


### Array to Set

```
Set<String>  set1 = new HashSet<>(Arrays.asList(StrArray));

Set<Integer> set2 = Arrays.stream(intArray)
                          .boxed()                   // int -> Integer
                          .collect(Collectors.toSet());

Set<String>  set3 = new HashSet<>();
Collections.addAll(set3, StrArray);
```


### List/Set to Array

```
//list to array
String[] array1 = list.stream().toArray(String[]::new);
String[] array2 = list.toArray(new String[0]);
String[] array3 = list.toArray(new String[list1.size()]);  //more efficient


//set to array
String[] array4 = set.stream().toArray(String[]::new);
String[] array5 = set.toArray(new String[0]);
String[] array6 = set.toArray(new String[set1.size()]);    //more efficient
```


### List <-> Set

```
 //list to set
Set<String> set = new HashSet<>(list1);

//set to list
LinkedList<String> list = new LinkedList<>(set4);
```


### Map to collection

```
Map<String,Integer> map = new HashMap<>();

//values
List<Integer> valueList = new ArrayList<>(map.values());
Set<Integer>  valueSet  = new HashSet<>(map.values());

//keys
List<String> keyList = new ArrayList<>(map.keySet());
Set<Integer> keySet  = new HashSet<>(map.values());
```

[CODE]()