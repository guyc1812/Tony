# TreeMap

A Red-Black tree based NavigableMap implementation.

This implementation provides guaranteed log(n) time cost for the 
containsKey, get, put and remove operations.


SortedMap m = Collections.synchronizedSortedMap(new TreeMap(...));




# Data Structure

private transient Entry<K,V> root;

Entry<K,V> implements Map.Entry<K,V> {
    K key;
    V value;
    Entry<K,V> left;
    Entry<K,V> right;
    Entry<K,V> parent;
    boolean color = BLACK;
}



# Constructor

public TreeMap() {
    comparator = null;
}

public TreeMap(Comparator<? super K> comparator) {
    this.comparator = comparator;
}

public TreeMap(Map<? extends K, ? extends V> m) {
    comparator = null;
    putAll(m);
}

public TreeMap(SortedMap<K, ? extends V> m) {
    comparator = m.comparator();
    try {
        buildFromSorted(m.size(), m.entrySet().iterator(), null, null);
    } catch (java.io.IOException cannotHappen) {
    } catch (ClassNotFoundException cannotHappen) {
    }
}




# apis

public K firstKey() 
public K lastKey()

public boolean replace(K key, V oldValue, V newValue)
public V replace(K key, V value)


# TreeMap<K,V> extends AbstractMap<K,V>

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



# TreeMap<K,V> implements NavigableMap<K,V>

public Map.Entry<K,V> firstEntry() 
public Map.Entry<K,V> lastEntry() 
public Map.Entry<K,V> pollFirstEntry() 
public Map.Entry<K,V> pollLastEntry()
public Map.Entry<K,V> lowerEntry(K key)
public K lowerKey(K key) 
public Map.Entry<K,V> floorEntry(K key)
public K floorKey(K key) 
public Map.Entry<K,V> ceilingEntry(K key) 
public K ceilingKey(K key) 
public Map.Entry<K,V> higherEntry(K key) 
public K higherKey(K key) 

public NavigableMap<K,V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
public SortedMap<K,V> subMap(K fromKey, K toKey) {
    return subMap(fromKey, true, toKey, false);
}
public NavigableMap<K,V> headMap(K toKey, boolean inclusive) 
public SortedMap<K,V> headMap(K toKey) {
    return headMap(toKey, false);
}
public NavigableMap<K,V> tailMap(K fromKey, boolean inclusive) 
public SortedMap<K,V> tailMap(K fromKey) {
    return tailMap(fromKey, true);
}

public NavigableSet<K> navigableKeySet() 
public NavigableSet<K> descendingKeySet()

public NavigableMap<K, V> descendingMap()



# Iterator

Iterator<K> keyIterator()

Iterator<K> descendingKeyIterator() 