# HashMap

### Basic

* Hash table extends *AbstractMap<K,V>* implements *Map<K,V>*
* Permits NULL-NULL.
    The put method to insert key value pair in HashMap checks for null key and stores it at the first location of the internal table array. 
* Provides constant-time performance for the basic operations get(), put()
* Use fail-fast iterators.
* No thread safe
    ```
    Map m = Collections.synchronizedMap(new HashMap(...))
    ```


### CAPACITY and LOAD_FACTOR

```
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

The capacity is the number of buckets in the hash table, <br>
and the initial capacity is simply the capacity at the time the hash table is created.  <br>
The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased.  <br>
When the number of entries in the hash table exceeds the product of the load factor and the current capacity, <br>
the hash table is rehashed (that is, internal data structures are rebuilt), <br>
so that the hash table has approximately twice the number of buckets.<br>

As a general rule, the default load factor (.75) offers a good tradeOff between time and space costs. 

threshold = CAPACITY * LOAD_FACTOR

### Data Structure
```
transient Node<K,V>[] table;

Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;
}
```


### Constructor

```
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);
}

public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}

public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    putMapEntries(m, false);
}
```


### apis

```
public int size()
public boolean isEmpty()
public V get(Object key)
public boolean containsKey(Object key)
public boolean containsValue(Object value) 
public V put(K key, V value)
public void putAll(Map<? extends K, ? extends V> m)
public V putIfAbsent(K key, V value)
public V remove(Object key) 
public boolean remove(Object key, Object value)
public void clear()

public Set<K> keySet()
public Collection<V> values()
public Set<Map.Entry<K,V>> entrySet()

public boolean replace(K key, V oldValue, V newValue) 
public V replace(K key, V value)

//AbstractMap
public int size()
public boolean isEmpty()
public V get(Object key)
public V put(K key, V value)
public void putAll(Map<? extends K, ? extends V> m)
public V remove(Object key) 
public boolean containsKey(Object key)
public boolean containsValue(Object value) 
public void clear()

public Set<K> keySet()
public Collection<V> values()
public Set<Map.Entry<K,V>> entrySet()
public boolean equals(Object o) 
public int hashCode()
public Object clone() 
```


### Iterator

```
// traversal of entrySet
Iterator iter1 = map.entrySet().iterator();
while (iter1.hasNext()) {
    Map.Entry entry = (Map.Entry) iter1.next();
    String k = (String) entry.getKey();
    int v = (Integer) entry.getValue();
}

// traversal of keySet
Iterator iter2 = map.keySet().iterator();
while (iter2.hasNext()) {
    String k = (String) iter2.next();
    int v = map.get(k);
}

// traversal of values
Collection c = map.values();
Iterator iter3 = c.iterator();
while (iter3.hasNext()) {
    int v = (Integer) iter3.next();
}

// traversal of entries
map.forEach((k, v) -> {
    String key = k;
    int value = v;
});
```


### Implementation of HASH

//todo


(https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/map/hashMap/code)
