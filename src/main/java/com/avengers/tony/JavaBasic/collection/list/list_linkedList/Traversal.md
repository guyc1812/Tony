# Traversal of ArrayList

### Get List

```
private static ArrayList getLinkedList(int num) {
    ArrayList linkedList = new ArrayList();
    for (int i = 0; i < num; i++) linkedList.add(i);
    return linkedList;
}
```

### Traversal

```
/* Traversal By Iterator */
private static void traversalByIterator(ArrayList<Integer> list) {
    if (list == null) return;
    Timer.start();
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
        int i = iterator.next();
    }
    Timer.end();
}

/* Traversal By ForEach */
private static void traversalByForEach(ArrayList<Integer> list) {
    if (list == null) return;
    Timer.start();
    for (Integer item : list){
        int i = item;
    }
    Timer.end();
}

/* Traversal By ForLoop */
private static void traversalByFor(ArrayList<Integer> list) {
    if (list == null) return;
    Timer.start();
    int size = list.size();
    for (int i = 0; i < size; i++) {
        int j = list.get(i);
    }
    Timer.end();
}

/* Traversal By Stream */
private static void traversalByStream(ArrayList<Integer> list) {
    if (list == null) return;
    ArrayList<Integer> n = new ArrayList(1000000);
    Timer.start();
    list.parallelStream().forEach(item->{
        int i = item;
    });
    Timer.end();
}
```

### Test

```
public static void main(String[] args) {
        
    ArrayList<Integer> t1 = getLinkedList(1000000);
    ArrayList<Integer> t2 = getLinkedList(1000000);
    ArrayList<Integer> t3 = getLinkedList(1000000);
    ArrayList<Integer> t4 = getLinkedList(1000000);
    
    traversalByIterator(t1);  //Timer : 11 ms  
    traversalByForEach(t2);   //Timer : 10 ms  
    traversalByFor(t3);       //Timer : 9 ms  
    traversalByStream(t4);    //Timer : 698 ms  
    
}
```