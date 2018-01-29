# Map

* [Java Map](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/map/docs/Map.md)
* [HashMap](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/map/hashMap/docs/HashMap.md)
* [HashTable](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/map/hashTable/docs/HashTable.md)
* TreeMap
    * [Basic](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/map/treeMap/docs/TreeMap.md)
    * [Usage](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/map/treeMap/docs/TreeMapUsage.md)


### Construction

* Map           Interface, Basic apis
* AbstractMap   abstract class implements Map,  implementing most Map apis.
* SortedMap     interface extends Map, supports sorting by Comparator.
* NavigableMap  interface extends SortedMap, supports navigable apis.
* TreeMap       class extends AbstractMap, implements NavigableMap, ordered entries.
* HashMap       class extends AbstractMap, no order.
* Hashtable     class extends Dictionary, Thread safe, supports both Iterator and Enumeration.
* WeakHashMap   class extends AbstractMap, but it's key is a WeakReference, which can be recycled by GC.


### APIs

* AbstractMap APIs

    ```
     void                 clear()
     boolean              containsKey(Object key)
     boolean              containsValue(Object value)
     boolean              equals(Object object)
     V                    get(Object key)
     int                  hashCode()
     boolean              isEmpty()
     Set<K>               keySet()
     V                    put(K key, V value)
     void                 putAll(Map<? extends K, ? extends V> map)
     V                    remove(Object key)
     int                  size()
     String               toString()
     Collection<V>        values()
     Object               clone()
    ```

* SortedMap APIs
  
    ```
    abstract K                         firstKey()
    abstract K                         lastKey()
    abstract SortedMap<K, V>           headMap(K endKey)
    abstract SortedMap<K, V>           tailMap(K startKey)
    abstract Comparator<? super K>     comparator()
    abstract SortedMap<K, V>           subMap(K startKey, K endKey)
    ```
    
* NavigableMap APIs.

    * Entry
        ```
        floorEntry      // <=
        lowerEntry      // <
        ceilingEntry    // >=
        higherEntry     // >
        ```
    * Key
        ```
        floorKey        // <=
        lowerKey        // <
        ceilingKey      // >=
        higherKey       // >
        ```
    * Value
        ```
        navigableKeySet
        descendingKeySet
        ```
    * SubEntries.
    
* Dictionary APIs

    ```
    abstract Enumeration<V>     elements()
    abstract V                  get(Object key)
    abstract boolean            isEmpty()
    abstract Enumeration<K>     keys()
    abstract V                  put(K key, V value)
    abstract V                  remove(Object key)
    abstract int                size()
    ```


### WeakHashMap

* Hash table based implementation of the Map interface, with weak keys.
* Both null values and the null key are supported.
* No thread Safe.
* Use Fail-Fast iterator.
* Each key object in a WeakHashMap is stored indirectly as the referent of a weak reference. 
* An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary use. 
* WeakHashMap holds a reference queue for cleared WeakEntries, and remove the entry automatically.

the WeakReference class
```
public class WeakReference<T> extends Reference<T> {

    public WeakReference(T referent) {
        super(referent);
    }

    public WeakReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

}
```
WeakHashMap Entry
```
private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V> {
    V value;
    final int hash;
    Entry<K,V> next;

    /**
     * Creates new entry.
     */
    Entry(Object key, V value,
          ReferenceQueue<Object> queue,
          int hash, Entry<K,V> next) {
        super(key, queue);
        this.value = value;
        this.hash  = hash;
        this.next  = next;
    }
}
```


### HashMap vs HashTable

* Implementation
    * HashMap extends AbstractMap
    * Hashtable extends Dictionary
* Thread Safe
    * Hashtable is thread safe, all the methods are wrapped with Synchronized.
    * HashMap is no thread safe.
        Use `ava.util.concurrent.ConcurrentHashMap` or
        Use `Map m = Collections.synchronizedMap(new HashMap(...))`
* NULL
    * HashMap permits null-null
    * Hashtable no permits null on both key and value.
* Iterator
    * HashMap supports Iterator (forward, 0 -> size).
    * Hashtable supports Iterator and Enumeration (backward, size -> 0).
* capacity and Increment
    * HashMap 16 -> 16 * 2
    * Hashtable 11 -> 11 * 2 + 1


### Why HashMap Permits Nulls but HashTable does not

Hashtable is the older class, and its use is generally discouraged. <br>
Since null isn't an object, you can't call .equals() or .hashCode() on it, <br>
so the Hashtable can't compute a hash to use it as a key. 

HashMap is newer, and has more advanced capabilities, <br>
which are basically just an improvement on the Hashtable functionality. <br>
When HashMap was created, it was specifically designed to handle null values as keys and handles them as a special case.


### HashMap vs ConcurrentHashMap

* Thread -Safe : 
    ConcurrentHashMap is thread-safe.<br>
    HashMap is not thread-safe.
    
* Null Key
    ConcurrentHashMap does not allow NULL values.<br>
    HashMap can only be one null key.


### ConcurrentHashMap vs SynchronizedHashMap

* ConcurrentHashMap
    Better performance when very high concurrency.<br>
    It is thread safe without synchronizing the whole map.<br>
    Reads can happen very fast while write is done with a lock.<br>
    There is no locking at the object level.<br>
    The locking is at a much finer granularity at a hashmap bucket level.<br>
    ConcurrentHashMap doesn’t throw a ConcurrentModificationException which means no fail-fast.<br>
    ConcurrentHashMap uses multitude of locks.
    
* SynchronizedHashMap
    Synchronization at Object level.<br>
    Every read/write operation needs to acquire lock.<br>
    Locking the entire collection is a performance overhead.<br>
    This essentially gives access to only one thread to the entire map & blocks all the other threads.<br>
    It may cause contention.<br>
    SynchronizedHashMap returns Iterator, which fails-fast on concurrent modification.


### Performance of HashMap

An instance of HashMap has two parameters that affect its performance: <br>
* initial capacity (16)
    The capacity is the number of buckets in the hash table
* load factor (.75)
    The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. <br>

When the number of entries in the hash table exceeds the product of the load factor and the current capacity, <br>
the hash table is rehashed (internal data structures are rebuilt),<br>
so that the hash table has approximately twice the number of buckets.


### How Hash Map Works

**HashMap works on the principle of Hashing.**

* Hash Function
    Hash Function is the hashCode() function which returns an integer value.<br>
    This method is present in Object class (Mother of all class).<br>
    ```
    public native int hashCode();
    ```

* Hash value
    Hash value is the int value returned by the hash function .
    
* bucket   
    bucket is used to store key value pairs.<br>
    A bucket can have multiple key-value pairs. <br>
    In hash map, bucket used simple linked list to store objects.


**HashMap get(Key k) method**

Get(Key k) calls hashCode method on the key object <br>
and applies returned hashValue to its own static hash function to find a bucket location. <br>
Both key and value is stored in the bucket as a form of Entry object. 

```
Public  V get(Object key){
     if (key ==null)
     //Some code
    
     int hash = hash(key.hashCode());       // calculate the hashvalue again
    
     // if key found in hash table then  return value
     // else return null
}
```

**why calculating the hash value again using hash(hashValue)**

It defends against poor quality of hash functions.

**What if  when two different keys have the same hashcode**

equals() method comes to rescue.<br>
Bucket is a simple linked list.<br>
We traverse through linked list, comparing keys in each entries using keys.equals() until it return true.  <br>
Then the corresponding entry object Value is returned .

**when an element is added/retrieved, same procedure follows:**

1. Using key.hashCode(), determine initial hash value for the key

2. Calculate the hash value again using hash(hashValue)

3. Get the index according to hashValue, and the linked list in the bucket is then iterated over:
    the end is found and the element is added or the key is matched and the value is returned

