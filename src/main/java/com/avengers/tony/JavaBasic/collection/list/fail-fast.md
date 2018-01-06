1. fail-fast简介

    fail-fast 机制是java集合(Collection)中的一种错误机制。
    
    当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。
    
    例如：当某一个线程A通过iterator去遍历某集合的过程中，
    若该集合的内容被其他线程所改变了；
    那么线程A访问集合时，
    就会抛出ConcurrentModificationException异常，
    产生fail-fast事件。

2. fail-fast解决办法
   
    fail-fast机制，是一种错误检测机制。它只能被用来检测错误，因为JDK并不保证fail-fast机制一定会发生。若在多线程环境下使用fail-fast机制的集合，建议使用“java.util.concurrent包下的类”去取代“java.util包下的类”。
    
    只需要将ArrayList替换成java.util.concurrent包下对应的类即可。
    
    即将
    ```
        private static List<String> list = new ArrayList<String>();
    ```
    替换为
    ```
        private static List<String> list = new CopyOnWriteArrayList<String>();
    ```
    
    则可以解决该办法。
   
3. fail-fast原理
   
   产生fail-fast事件，是通过抛出ConcurrentModificationException异常来触发的。
   
   iterator 调用 next() 和 remove()时，
   都会执行 checkForComodification()。
   
   对于集合操作，无论是add()、remove()，还是clear()，只要涉及到修改集合中的元素个数时，都会改变modCount的值。
   
   若 “modCount 不等于 expectedModCount”，则抛出ConcurrentModificationException异常，产生fail-fast事件。
   
4. 和ArrayList继承于AbstractList不同，CopyOnWriteArrayList没有继承于AbstractList，它仅仅只是实现了List接口。
   
   ArrayList的iterator()函数返回的Iterator是在AbstractList中实现的；而CopyOnWriteArrayList是自己实现Iterator。
   
   ArrayList的Iterator实现类中调用next()时，会“调用checkForComodification()比较‘expectedModCount’和‘modCount’的大小”；但是，CopyOnWriteArrayList的Iterator实现类中，没有所谓的checkForComodification()，更不会抛出ConcurrentModificationException异常！ 
   
    ```
    ConcurrentHashMap 
    ConcurrentLinkedDeque 
    ConcurrentLinkedQueue 
    ConcurrentSkipListMap 
    ConcurrentSkipListSet 
    CopyOnWriteArrayList 
    CopyOnWriteArraySet 
    ```