# TreeSet


### Basic

* TreeSet<E> extends *AbstractSet<E>* implements *NavigableSet<E>*
* Permit NULL
* Provide guaranteed log(n) time cost for add(), remove() and contains().
* Do not guarantee that the order
* Iterating over this set requires time proportional
to the sum of the HashSet instance's size (the number of elements) 
plus the "capacity" of the backing HashMap instance (the number of buckets).  
Thus, it's very important not to set the initial capacity too high 
(or the load factor too low) if iteration performance is important.
* Use fail-fast iterators.
* No thread safe
    ```
    SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
    ```


### Data Structure

```
private transient NavigableMap<E,Object> m;
```


### Constructor

```
public TreeSet() {
    this(new TreeMap<E,Object>());
}

public TreeSet(Comparator<? super E> comparator) {
    this(new TreeMap<>(comparator));
}

public TreeSet(Collection<? extends E> c) {
    this();
    addAll(c);
}

public TreeSet(SortedSet<E> s) {
    this(s.comparator());
    addAll(s);
}
```


### Apis

* Basic api

    ```
    public int size()
    
    public boolean isEmpty()
    
    public boolean contains(Object o)
    
    public boolean add(E e)
    
    public  boolean addAll(Collection<? extends E> c)
    
    public boolean remove(Object o)
    
    public void clear()
    
    public Object clone()
    
    public NavigableSet<E> descendingSet() {
        return new TreeSet<>(m.descendingMap());
    }
    
    public  boolean addAll(Collection<? extends E> c)
    
    ```

* TreeSet<E> extends AbstractSet<E>

    ```
    public boolean equals(Object o)
    public int hashCode()
    public boolean removeAll(Collection<?> c) 
    ```

* TreeSet<E> implements NavigableSet<E>

    ```
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive)
    public NavigableSet<E> headSet(E toElement, boolean inclusive)
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive)
    
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }
    
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }
    
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }
    
    public E first() { return m.firstKey(); }
    public E last() { return m.lastKey(); }
    public E lower(E e) { return m.lowerKey(e); }
    public E floor(E e) { return m.floorKey(e); }
    public E ceiling(E e) { return m.ceilingKey(e); }
    public E higher(E e) { return m.higherKey(e); }
    
    public E pollFirst()
    public E pollLast()
    ```

* Iterator

    ```
    public Iterator<E> iterator() {
        return m.navigableKeySet().iterator();
    }
    
    public Iterator<E> descendingIterator() {
        return m.descendingKeySet().iterator();
    }
    ```













