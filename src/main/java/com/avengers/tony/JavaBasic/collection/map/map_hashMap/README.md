* HashMap简介

    * HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。
    * HashMap 继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口。
    * HashMap 的实现不是同步的，这意味着它不是线程安全的。
    * HashMap 的key、value都可以为null。此外，HashMap中的映射不是有序的。
    * HashMap 的实例有两个参数影响其性能：
    
        “初始容量” 和 “加载因子”。
        
        容量是哈希表中桶的数量，初始容量 只是哈希表在创建时的容量。
        
        加载因子 是哈希表在其容量自动增加之前可以达到多满的一种尺度。
        
        当哈希表中的条目数超出了加载因子与当前容量的乘积时，
        则要对该哈希表进行 rehash 操作（即重建内部数据结构），从而哈希表将具有大约两倍的桶数。
        
        通常，默认加载因子是 0.75, 这是在时间和空间成本上寻求一种折衷。
        在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少 rehash 操作次数。
        如果初始容量大于最大条目数除以加载因子，则不会发生 rehash 操作。
        
* HashMap的构造函数

    ```
    // 默认构造函数。
    HashMap()
    
    // 指定“容量大小”的构造函数
    HashMap(int capacity)
    
    // 指定“容量大小”和“加载因子”的构造函数
    HashMap(int capacity, float loadFactor)
    
    // 包含“子Map”的构造函数
    HashMap(Map<? extends K, ? extends V> map)
    ```
    
* HashMap的API
  
    ```
    void                 clear()
    Object               clone()
    boolean              containsKey(Object key)
    boolean              containsValue(Object value)
    Set<Entry<K, V>>     entrySet()
    V                    get(Object key)
    boolean              isEmpty()
    Set<K>               keySet()
    V                    put(K key, V value)
    void                 putAll(Map<? extends K, ? extends V> map)
    V                    remove(Object key)
    int                  size()
    Collection<V>        values()
    ```
    
* HshMap的结构
   
    HashMap是通过"拉链法"实现的哈希表。它包括几个重要的成员变量：table, size, threshold, loadFactor, modCount。
        * table是一个Entry[]数组类型，而Entry实际上就是一个单向链表。哈希表的"key-value键值对"都是存储在Entry数组中的。 
        * size是HashMap的大小，它是HashMap保存的键值对的数量。 
        * threshold是HashMap的阈值，用于判断是否需要调整HashMap的容量。threshold的值="容量*加载因子"，当HashMap中存储数据的数量达到threshold时，就需要将HashMap的容量加倍。
        * loadFactor就是加载因子。 
        * modCount是用来实现fail-fast机制的。
        
