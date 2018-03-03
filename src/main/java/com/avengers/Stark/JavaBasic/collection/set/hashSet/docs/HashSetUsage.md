# HashSet Usage

### new HashSet

```java
HashSet<String> set = new HashSet<>();
set.add("a");
set.add("b");
set.add("c");
set.add("d");
set.add("e");           // [a, b, c, d, e]
```


### Basic Usage

```java
set.size();             // 5

set.contains("a");      // true
set.contains("f");      // false

set.remove("e");        // [a, b, c, d]

HashSet newSet = new HashSet();
newSet.add("a");
newSet.add("c");
newSet.add("f");        // [a, c, f]

HashSet removeSet = (HashSet)set.clone();
HashSet retainSet = (HashSet)set.clone();

removeset.removeAll(newSet);      // removeset = [b, d]

retainset.retainAll(newSet);      // retainset = [a, c]

set.clear();

set.isEmpty();          // true
```


### Traversal

```java
// Iterator: recommend way
for(Iterator iterator = set.iterator(); iterator.hasNext(); ) {
    System.out.print(iterator.next());
}

// for-each: need to convert set to array first
String[] arr = set.toArray(new String[0]);
for (String str:arr){
    System.out.print(str);
}
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/set/hashSet/code)
