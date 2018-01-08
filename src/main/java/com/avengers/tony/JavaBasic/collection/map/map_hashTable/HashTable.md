# HashTable

To successfully store and retrieve objects from a hashtable, 
the objects used as keys must implement the hashCode method and the equals method.

NULL?





# Data Structure

private transient Entry<?,?>[] table;

Entry<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Entry<K,V> next;
}


# Constructor

public Hashtable(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal Load: "+loadFactor);
    if (initialCapacity==0)
        initialCapacity = 1;
    this.loadFactor = loadFactor;
    table = new Entry<?,?>[initialCapacity];
    threshold = (int)Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
}

public Hashtable(int initialCapacity) {
    this(initialCapacity, 0.75f);
}

public Hashtable() {
    this(11, 0.75f);
}

public Hashtable(Map<? extends K, ? extends V> t) {
    this(Math.max(2*t.size(), 11), 0.75f);
    putAll(t);
}


# apis

// all the methods is synchronized

// Hashtable<K,V> extends Dictionary<K,V>
public int size()
public boolean isEmpty()
public Enumeration<K> keys()
public Enumeration<V> elements()
public V get(Object key)
public V put(K key, V value)
public V remove(Object key)

interface Enumeration<E> {
    boolean hasMoreElements();
    E nextElement();
}

public synchronized int size()
public synchronized boolean isEmpty()
public synchronized Enumeration<K> keys()
public synchronized Enumeration<V> elements()   //values
public synchronized boolean contains(Object value)

public boolean containsValue(Object value) {
    return contains(value); //public synchronized boolean contains(Object value)
}
public synchronized boolean containsKey(Object key) 
public synchronized V get(Object key)
public synchronized V put(K key, V value)
public synchronized V putIfAbsent(K key, V value)
public synchronized V remove(Object key)
public synchronized boolean remove(Object key, Object value)
public synchronized void putAll(Map<? extends K, ? extends V> t)
public synchronized void clear()
public synchronized Object clone()

public synchronized boolean replace(K key, V oldValue, V newValue)
public synchronized V replace(K key, V value) 



public Set<K> keySet() {
    if (keySet == null)
        keySet = Collections.synchronizedSet(new KeySet(), this);
    return keySet;
}
public Set<Map.Entry<K,V>> entrySet() {
    if (entrySet==null)
        entrySet = Collections.synchronizedSet(new EntrySet(), this);
    return entrySet;
}
public Collection<V> values() {
    if (values==null)
        values = Collections.synchronizedCollection(new ValueCollection(), this);
    return values;
}
public synchronized boolean equals(Object o)
public synchronized int hashCode() 



# iterator