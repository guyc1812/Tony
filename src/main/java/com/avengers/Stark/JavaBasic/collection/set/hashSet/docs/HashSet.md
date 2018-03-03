# HashSet


### Basic

* HashSet<E> extends AbstractSet<E> implements Set<E>, backed by a hashMap
* Permit NULL
* Do not guarantee that the order
* offers constant time performance for add(), remove(), contains(), size().
* Iterating over this set requires time proportional
to the sum of the HashSet instance's size (the number of elements) 
plus the "capacity" of the backing HashMap instance (the number of buckets).  
Thus, it's very important not to set the initial capacity too high 
(or the load factor too low) if iteration performance is important.
* Use fail-fast iterators.
* No thread safe
    ```java
    Set s = Collections.synchronizedSet(new HashSet(...));
    ```


### Data Structure

```java
private transient HashMap<E,Object> map;
```


### Constructor

```java
public HashSet() {
    map = new HashMap<>();
}

public HashSet(Collection<? extends E> c) {
    map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    addAll(c);
}

public HashSet(int initialCapacity, float loadFactor) {
    map = new HashMap<>(initialCapacity, loadFactor);
}

public HashSet(int initialCapacity) {
    map = new HashMap<>(initialCapacity);
}
```


### Apis

* Basic api

    ```java
    public int size()
    
    public boolean isEmpty()
    
    public boolean contains(Object o)
    
    public boolean add(E e)
    
    public boolean remove(Object o)
    
    public void clear()
    
    public Object clone()
    
    ```


* HashSet<E> extends AbstractSet<E>

    ```java
    public boolean equals(Object o)
    public int hashCode()
    public boolean removeAll(Collection<?> c) 
    ```


* AbstractSet<E> extends AbstractCollection<E>

    ```java
    public Object[] toArray()
    public <T> T[] toArray(T[] a)
    public boolean containsAll(Collection<?> c) 
    public boolean addAll(Collection<? extends E> c)
    public boolean removeAll(Collection<?> c)
    public boolean retainAll(Collection<?> c)
    ```


* Iterator

    ```java
    /**
     * Returns an iterator over the elements in this set.  The elements
     * are returned in no particular order.
     *
     * @return an Iterator over the elements in this set
     * @see ConcurrentModificationException
     */
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
    ```













