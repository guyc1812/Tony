# Map 架构

* Map 是映射接口，Map中存储的内容是键值对(key-value)。
* AbstractMap 是继承于Map的抽象类，它实现了Map中的大部分API。其它Map的实现类可以通过继承AbstractMap来减少重复编码。
* SortedMap 是继承于Map的接口。SortedMap中的内容是排序的键值对，排序的方法是通过比较器(Comparator)。
* NavigableMap 是继承于SortedMap的接口。相比于SortedMap，NavigableMap有一系列的导航方法；如"获取大于/等于某对象的键值对"、“获取小于/等于某对象的键值对”等等。 
* TreeMap 继承于AbstractMap，且实现了NavigableMap接口；因此，TreeMap中的内容是“有序的键值对”！
* HashMap 继承于AbstractMap，但没实现NavigableMap接口；因此，HashMap的内容是“键值对，但不保证次序”！
* Hashtable 虽然不是继承于AbstractMap，但它继承于Dictionary(Dictionary也是键值对的接口)，而且也实现Map接口；因此，Hashtable的内容也是“键值对，也不保证次序”。但和HashMap相比，Hashtable是线程安全的，而且它支持通过Enumeration去遍历。
* WeakHashMap 继承于AbstractMap。它和HashMap的键类型不同，WeakHashMap的键是“弱键”。
    “弱键”是一个“弱引用(WeakReference)”，在Java中，WeakReference和ReferenceQueue 是联合使用的。
    在WeakHashMap中亦是如此：如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。 
    接着，WeakHashMap会根据“引用队列”，来删除“WeakHashMap中已被GC回收的‘弱键’对应的键值对”。


# HashMap vs HashTable

1. 继承和实现方式不同

    * HashMap 继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口。
    * Hashtable 继承于Dictionary，实现了Map、Cloneable、java.io.Serializable接口。
    
2. 线程安全不同
    
    * Hashtable的几乎所有函数都是同步的，即它是线程安全的，支持多线程。
    * HashMap的函数则是非同步的，它不是线程安全的。
    
        若要在多线程中使用HashMap，需要我们额外的进行同步处理。 
        对HashMap的同步处理可以使用Collections类提供的synchronizedMap静态方法，
        或者直接使用JDK 5.0之后提供的java.util.concurrent包里的ConcurrentHashMap类。
        
3. 对null值的处理不同

    * HashMap的key、value都可以为null。
    * Hashtable的key、value都不可以为null。
    
4. 支持的遍历种类不同
    
    * HashMap只支持Iterator(迭代器)遍历。
    * Hashtable支持Iterator(迭代器)和Enumeration(枚举器)两种方式遍历。
    
5. 通过Iterator迭代器遍历时，遍历的顺序不同
    
    * HashMap是“从前向后”的遍历数组；再对数组具体某一项对应的链表，从表头开始进行遍历。
    * Hashtable是“从后往前”的遍历数组；再对数组具体某一项对应的链表，从表头开始进行遍历。
    
6. 容量的初始值 和 增加方式都不一样
    
    * HashMap默认的容量大小是16；增加容量时，每次将容量变为“原始容量x2”。
    * Hashtable默认的容量大小是11；增加容量时，每次将容量变为“原始容量x2 + 1”。
       

# HashMap vs WeakHashMap

* 相同点

    1. 它们都是散列表，存储的是“键值对”映射。
    2. 它们都继承于AbstractMap，并且实现Map基础。
    3. 它们的构造函数都一样。它们都包括4个构造函数，而且函数的参数都一样。
    4. 默认的容量大小是16，默认的加载因子是0.75。
    5. 它们的“键”和“值”都允许为null。
    6. 它们都是“非同步的”。

* 不同点

    1. HashMap实现了Cloneable和Serializable接口，而WeakHashMap没有。
        
        HashMap实现Cloneable，意味着它能通过clone()克隆自己。
        
        HashMap实现Serializable，意味着它支持序列化，能通过序列化去传输。

    2. HashMap的“键”是“强引用(StrongReference)”，而WeakHashMap的键是“弱引用(WeakReference)”。
        
        WeakReference的“弱键”能实现WeakReference对“键值对”的动态回收。当“弱键”不再被使用到时，GC会回收它，WeakReference也会将“弱键”对应的键值对删除。

        这个“弱键”实现的动态回收“键值对”的原理呢？其实，通过WeakReference(弱引用)和ReferenceQueue(引用队列)实现的。 首先，我们需要了解WeakHashMap中：
        
        第一，“键”是WeakReference，即key是弱键。
        
        第二，ReferenceQueue是一个引用队列，它是和WeakHashMap联合使用的。当弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。 WeakHashMap中的ReferenceQueue是queue。
        
        第三，WeakHashMap是通过数组实现的，我们假设这个数组是table。
        

# How Hash Map Works In Java

*HashMap works on the principle of Hashing.* 
To understand Hashing, we should understand the three terms first: <br>
Hash Function, Hash Value and Bucket.

**Hash Function** is the hashCode() function  which returns an integer value is the Hash function. <br>
The important point to note that,  this method is present in Object class (Mother of all class).<br>
This is the code for the hash function(also known as hashCode method) in Object Class :<br>
```
    public native int hashCode();
```

**Hash value** is the int value returned by the hash function .

**bucket** is used to store key value pairs . A bucket can have multiple key-value pairs . In hash map, bucket used simple linked list to store objects .

HashMap get(Key k) method 
calls hashCode method on the key object and applies returned hashValue to its own static hash function to find a bucket location where keys and values are stored in form of a nested class called Entry (Map.Entry) . 

So Both key and value is stored in the bucket as a form of Entry object. 

```
Public  V get(Object key){
     if (key ==null)
     //Some code
    
     int hash = hash(key.hashCode());       // calculate the hashvalue again
    
     // if key found in hash table then  return value
     //    else return null
}
```

why we are calculating the hashvalue again using hash(hashValue)?

Answer is ,It defends against poor quality hash functions.

What if  when two different keys have the same hashcode ?

equals() method comes to rescue.
bucket is a simple linked list.
We traverse through linked list , comparing keys in each entries using keys.equals() until it return true.  Then the corresponding entry object Value is returned .


when an element is added/retrieved, same procedure follows:

a. Using key.hashCode(), determine initial hashvalue for the key

b. Pass intial hashvalue as hashValue in hash(hashValue) function, to calculate the final hashvalue.

c. Final hash value is then passed as a first parameter in the indexFor(int ,int )method .
    
    The second parameter is length which is a constant in HashMap Java Api , 
    represented by DEFAULT_INITIAL_CAPACITY = 16

    indexFor(int,int) method  returns the first entry in the appropriate bucket. 
    The linked list in the bucket is then iterated over - (the end is found and the element is added or the key is matched and the value is returned )


# How will you measure the performance of HashMap?

An instance of HashMap has two parameters that affect its performance: <br>
initial capacity and load factor. <br>

The capacity is the number of buckets in the hash table( HashMap class is roughly equivalent to Hashtable,<br>
except that it is unsynchronized and permits nulls.), and the initial capacity is simply the capacity at the time the hash table is created. 

The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. <br>
When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.

In HashMap class, the default value of load factor is (.75) .


# HashMap vs ConcurrentHashMap

1.  Thread -Safe : 

     ConcurrentHashMap is thread-safe that is the code can be accessed by single thread at a time .    
     while HashMap is not thread-safe .

2.  Synchronization Method :

    HashMap can be synchronized by using synchronizedMap(HashMap)  method .  
    By using this method we get a HashMap object which is equivalent to the HashTable object . 
    So every modification  is performed on  Map is locked on Map object.
    ```
    Map<String,String> syncMap = Collections.synchronizedMap(map);
    ```

3.  Null Key

     ConcurrentHashMap does not allow NULL values .
     While In HashMap there can only be one null key .
  
4.  Performance 

     In multiple threaded environment HashMap is usually faster than ConcurrentHashMap . 
     Asonly single thread can access the certain portion of the Map and thus reducing the performance . 
     While in HashMap any number of threads can access the code at the same time .


# ConcurrentHashMap vs SynchronizedHashMap

* ConcurrentHashMap

    You should use ConcurrentHashMap when you need very high concurrency in your project.
    It is thread safe without synchronizing the whole map.
    Reads can happen very fast while write is done with a lock.
    There is no locking at the object level.
    The locking is at a much finer granularity at a hashmap bucket level.
    ConcurrentHashMap doesn’t throw a ConcurrentModificationException if one thread tries to modify it while another is iterating over it.
    ConcurrentHashMap uses multitude of locks.
    
* SynchronizedHashMap

    Synchronization at Object level.
    Every read/write operation needs to acquire lock.
    Locking the entire collection is a performance overhead.
    This essentially gives access to only one thread to the entire map & blocks all the other threads.
    It may cause contention.
    SynchronizedHashMap returns Iterator, which fails-fast on concurrent modification.


