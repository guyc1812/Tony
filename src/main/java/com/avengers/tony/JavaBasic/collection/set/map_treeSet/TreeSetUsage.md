# TreeSet Usage

### Basic Usage

```
TreeSet tSet = new TreeSet();
    tSet.add("aaa");
    tSet.add("aaa");
    tSet.add("bbb");
    tSet.add("eee");
    tSet.add("ddd");
    tSet.add("ccc");

    tSet.size();                // 5
    
    // floor(<=)
    tSet.floor("bbb");          // bbb
    // lower(<)
    tSet.lower("bbb");          // aaa
    
    // ceiling(>=)
    tSet.ceiling("bbb");        // bbb
    tSet.ceiling("eee");        // eee
    // higher(>)
    tSet.higher("bbb");         // ccc
    
    // subSet()
    tSet.subSet("aaa", true, "ccc", true);      // [aaa, bbb, ccc]
    tSet.subSet("aaa", true, "ccc", false);     // [aaa, bbb]
    tSet.subSet("aaa", false, "ccc", true);     // [bbb, ccc]
    tSet.subSet("aaa", false, "ccc", false);    // [bbb]
        
    // headSet()
    tSet.headSet("ccc", true);          // [aaa, bbb, ccc]
    tSet.headSet("ccc", false);         // [aaa, bbb]
    
    // tailSet()
    tSet.tailSet("ccc", true);          // [ccc, ddd, eee]
    tSet.tailSet("ccc", false);         // [ddd, eee]

    // pollFirst
    String val1 = (String)tSet.pollFirst();     // aaa

    // pollLast
    String valn = (String)tSet.pollLast();      // eee

    // remove
    tSet.remove("ccc");                
    
    // to array
    String[] arr = (String[])tSet.toArray(new String[0]);
                                        // [aaa, bbb, ddd, eee]
    tSet.clear();
    tSet.isEmpty();
```

### Traversal

```
// asc
public static void ascIteratorThroughIterator(TreeSet set) {
    System.out.print("\n ---- Ascend Iterator ----\n");
    for(Iterator iter = set.iterator(); iter.hasNext(); ) {
        System.out.printf("asc : %s\n", iter.next());
    }
}

// desc
public static void descIteratorThroughIterator(TreeSet set) {
    System.out.printf("\n ---- Descend Iterator ----\n");
    for(Iterator iter = set.descendingIterator(); iter.hasNext(); )
        System.out.printf("desc : %s\n", (String)iter.next());
}

// for-each
private static void foreachTreeSet(TreeSet set) {
    System.out.printf("\n ---- For-each ----\n");
    String[] arr = (String[])set.toArray(new String[0]);
    for (String str:arr)
        System.out.printf("for each : %s\n", str);
}

```

output
```
 ---- Ascend Iterator ----
asc : aaa
asc : bbb
asc : ccc
asc : ddd
asc : eee

 ---- Descend Iterator ----
desc : eee
desc : ddd
desc : ccc
desc : bbb
desc : aaa

 ---- For-each ----
for each : aaa
for each : bbb
for each : ccc
for each : ddd
for each : eee
```