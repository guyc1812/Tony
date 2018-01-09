# Traversal of Vector

### Get Vector

```
private static Vector getVector(int num) {
    Vector<Integer> vector = new Vector<>();
    for (int i = 0; i < num; i++) vector.add(i);
    return vector;
}
```

### Traversal

```
/* Traversal By Iterator */
private static void traversalByIterator(Vector<Integer> vector) {
    if (vector == null) return;
    Timer.start();
    Iterator<Integer> iterator = vector.listIterator();
    while (iterator.hasNext()) {
        int i = iterator.next();
    }
    Timer.end();
}

/* Traversal By Enumeration */
private static void traversalByEnumeration(Vector<Integer> vector) {
    if (vector == null) return;
    Timer.start();
    Enumeration enu = vector.elements();
    while (enu.hasMoreElements()) {
        int i = (int)enu.nextElement();
    }
    Timer.end();
}

/* Traversal By ForEach */
private static void traversalByForEach(Vector<Integer> vector) {
    if (vector == null) return;
    Timer.start();
    for (Integer item : vector){
        int i = item;
    }
    Timer.end();
}

/* Traversal By ForLoop => avoid this */
private static void traversalByForLoop(Vector<Integer> vector) {
    if (vector == null) return;
    Timer.start();
    for (int i = 0; i < vector.size(); i++) {
        int v = vector.get(i);
    }
    Timer.end();
}
```

### Test

```
public static void main(String[] args) {

    traversalByIterator(getVector(100000));
        // Timer : 6 ms
    traversalByEnumeration(getVector(100000));
        // Timer : 7 ms
    traversalByForEach(getVector(100000));
        // Timer : 6 ms
    traversalByForLoop(getVector(100000));
        // Timer : 6 ms

}
```