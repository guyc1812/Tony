# fail-fast

A fail-fast system is nothing but immediately report any failure that is likely to lead to failure. <br>

When you have called iterator on a collection object, <br>
and another thread tries to modify the collection object, <br>
then **ConcurrentModificationException** will be thrown. <br>
This is called fail-fast.<br>

reason:<br>
when a iterator calls the next() or remove(), <br>
the checkForComodification() function will be called first,<br>
no matter what operation to the collection like add(), remove() or clear(), <br>
as long as the number of elements in this collection is changed, the modCount state changes too, <br>
and once the modCount dose not equal the expectedModCount,<br>
then ConcurrentModificationException will be thrown.

The fail-fast iterator is implemented in the AbstractList class, <br>
CopyOnWriteArrayList do not extents the AbstractList, <br>
so there is no fail-fast in the CopyOnWriteArrayList.<br>

Solution:

relpace

```
    private static List<String> list = new ArrayList<String>();
```

with

```
    private static List<String> list = new CopyOnWriteArrayList<String>();
```

```
ConcurrentHashMap 
ConcurrentLinkedDeque 
ConcurrentLinkedQueue 
ConcurrentSkipListMap 
ConcurrentSkipListSet 
CopyOnWriteArrayList 
CopyOnWriteArraySet 

```