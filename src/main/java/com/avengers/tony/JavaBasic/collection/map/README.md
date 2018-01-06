* Map 架构

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

* Map提供接口分别用于返回 键集、值集或键-值映射关系集。
  
    * entrySet()用于返回键-值集的Set集合
    * keySet()用于返回键集的Set集合
    * values()用户返回值集的Collection集合
    * 因为Map中不能包含重复的键；每个键最多只能映射到一个值。所以，键-值集、键集都是Set，值集时Collection。
    
* Map.Entry的API
    ```
        abstract boolean     equals(Object object)
        abstract K             getKey()
        abstract V             getValue()
        abstract int         hashCode()
        abstract V             setValue(V object)
    ```

* AbstractMap的API

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

* SortedMap是一个继承于Map接口的接口。它是一个有序的SortedMap键值映射。
  
  SortedMap的排序方式有两种：自然排序 或者 用户指定比较器。 
  
  插入有序 SortedMap 的所有元素都必须实现 Comparable 接口（或者被指定的比较器所接受）。
  
  另外，所有SortedMap 实现类都应该提供 4 个“标准”构造方法
  * void（无参数）构造方法，它创建一个空的有序映射，按照键的自然顺序进行排序。
  * 带有一个 Comparator 类型参数的构造方法，它创建一个空的有序映射，根据指定的比较器进行排序。
  * 带有一个 Map 类型参数的构造方法，它创建一个新的有序映射，其键-值映射关系与参数相同，按照键的自然顺序进行排序。
  * 带有一个 SortedMap 类型参数的构造方法，它创建一个新的有序映射，其键-值映射关系和排序方法与输入的有序映射相同。无法保证强制实施此建议，因为接口不能包含构造方法。
  
    ```
        // 继承于Map的API
        abstract void                 clear()
        abstract boolean              containsKey(Object key)
        abstract boolean              containsValue(Object value)
        abstract Set<Entry<K, V>>     entrySet()
        abstract boolean              equals(Object object)
        abstract V                    get(Object key)
        abstract int                  hashCode()
        abstract boolean              isEmpty()
        abstract Set<K>               keySet()
        abstract V                    put(K key, V value)
        abstract void                 putAll(Map<? extends K, ? extends V> map)
        abstract V                    remove(Object key)
        abstract int                  size()
        abstract Collection<V>        values()
        // SortedMap新增的API 
        abstract Comparator<? super K>     comparator()
        abstract K                         firstKey()
        abstract SortedMap<K, V>           headMap(K endKey)
        abstract K                         lastKey()
        abstract SortedMap<K, V>           subMap(K startKey, K endKey)
        abstract SortedMap<K, V>           tailMap(K startKey)
    ```
    
* NavigableMap是继承于SortedMap的接口。它是一个可导航的键-值对集合.

    NavigableMap除了继承SortedMap的特性外，它的提供的功能可以分为4类：
    * 提供操作键-值对的方法。
        * lowerEntry、floorEntry、ceilingEntry 和 higherEntry 方法，它们分别返回与小于、小于等于、大于等于、大于给定键的键关联的 Map.Entry 对象。
        * firstEntry、pollFirstEntry、lastEntry 和 pollLastEntry 方法，它们返回和/或移除最小和最大的映射关系（如果存在），否则返回 null。
    * 提供操作键的方法。这个和第1类比较类似
        * lowerKey、floorKey、ceilingKey 和 higherKey 方法，它们分别返回与小于、小于等于、大于等于、大于给定键的键。
    * 获取键集。
        * navigableKeySet、descendingKeySet分别获取正序/反序的键集。
    * 获取键-值对的子集。
    
* Dictionary的API

    ```
        abstract Enumeration<V>     elements()
        abstract V                  get(Object key)
        abstract boolean            isEmpty()
        abstract Enumeration<K>     keys()
        abstract V                  put(K key, V value)
        abstract V                  remove(Object key)
        abstract int                size()
    ```

* HashMap vs HashTable

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
       

* HashMap vs WeakHashMap

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
        
