* HashTable简介

    * Hashtable 的函数都是同步的，这意味着它是线程安全的。
    * Hashtable 的key、value都不可以为null。此外，Hashtable中的映射不是有序的。
        
* Hashtable的构造函数

    ```
    // 默认构造函数。
    Hashtable()
    
    // 指定“容量大小”的构造函数
    Hashtable(int capacity)
    
    // 指定“容量大小”和“加载因子”的构造函数
    Hashtable(int capacity, float loadFactor)
    
    // 包含“子Map”的构造函数
    Hashtable(Map<? extends K, ? extends V> map)
    ```
    
* HashMap的API
  
    ```
    synchronized void                clear()
    synchronized Object              clone()
                 boolean             contains(Object value)
    synchronized boolean             containsKey(Object key)
    synchronized boolean             containsValue(Object value)
    synchronized Enumeration<V>      elements()
    synchronized Set<Entry<K, V>>    entrySet()
    synchronized boolean             equals(Object object)
    synchronized V                   get(Object key)
    synchronized int                 hashCode()
    synchronized boolean             isEmpty()
    synchronized Set<K>              keySet()
    synchronized Enumeration<K>      keys()
    synchronized V                   put(K key, V value)
    synchronized void                putAll(Map<? extends K, ? extends V> map)
    synchronized V                   remove(Object key)
    synchronized int                 size()
    synchronized String              toString()
    synchronized Collection<V>       values()
    ```
    
* HshMap的结构
   
    HashMap是通过"拉链法"实现的哈希表。它包括几个重要的成员变量：table, size, threshold, loadFactor, modCount。
        * table是一个Entry[]数组类型，而Entry实际上就是一个单向链表。哈希表的"key-value键值对"都是存储在Entry数组中的。 
        * size是HashMap的大小，它是HashMap保存的键值对的数量。 
        * threshold是HashMap的阈值，用于判断是否需要调整HashMap的容量。threshold的值="容量*加载因子"，当HashMap中存储数据的数量达到threshold时，就需要将HashMap的容量加倍。
        * loadFactor就是加载因子。 
        * modCount是用来实现fail-fast机制的。
        
