# fail-fast

A fail-fast system is nothing but immediately report any failure that
is likely to lead to failure. 

When you have called iterator on a collection object, and another
thread tries to modify the collection object, then concurrent modification
exception will be thrown. This is called fail-fast.

reason:<br>
when a iterator calls the next() or remove(), <br>
the checkForComodification() function will be called first,<br>
no matter what operation to the collection like add(), remove() or clear(), <br>
as long as the number of elements in this collection is changed, the modCount state changes too, <br>
and once the modCount dose not equal the expectedModCount,<br>
then ConcurrentModificationException will be thrown

The fail-fast iterator is implemented in the AbstractList class, <br>
CopyOnWriteArrayList do not extents the AbstractList, 
so there is no fail-fast in the CopyOnWriteArrayList.

Solution:

relpace
```
    private static List<String> list = new ArrayList<String>();
```
with
```
    private static List<String> list = new CopyOnWriteArrayList<String>();
```
```
ConcurrentHashMap 
ConcurrentLinkedDeque 
ConcurrentLinkedQueue 
ConcurrentSkipListMap 
ConcurrentSkipListSet 
CopyOnWriteArrayList 
CopyOnWriteArraySet 

```

# Describe and compare fail-fast and fail-safe iterators. Give examples.

The main distinction between fail-fast and fail-safe iterators is whether or not the collection can be modified while it is being iterated. Fail-safe iterators allow this; fail-fast iterators do not.

Fail-fast iterators operate directly on the collection itself. During iteration, fail-fast iterators fail as soon as they realize that the collection has been modified and will throw a ConcurrentModificationException. Some examples include ArrayList, HashSet, and HashMap (most JDK1.4 collections are implemented to be fail-fast).

Fail-safe iterates operate on a cloned copy of the collection and therefore do not throw an exception if the collection is modified during iteration. Examples would include iterators returned by ConcurrentHashMap or CopyOnWriteArrayList.


# ArrayList, LinkedList, Vector, Stack是List的4个实现类。
    
* ArrayList 是一个数组队列，相当于动态数组。它由数组实现，随机访问效率高，随机插入、随机删除效率低。
* LinkedList 是一个双向链表。它也可以被当作堆栈、队列或双端队列进行操作。LinkedList随机访问效率低，但随机插入、随机删除效率高。
* Vector 是矢量队列，和ArrayList一样，它也是一个动态数组，由数组实现。但是ArrayList是非线程安全的，而Vector是线程安全的。
* Stack 是栈，它继承于Vector。它的特性是：先进后出(FILO, First In Last Out)。


# When search an element in the LinkedList, if the index is less than the half of length, then the traversal from start to the end, otherwise, from the end to start.

# ArrayList, LinkedList, and Vector.

Of the three, LinkedList is generally going to give you the best performance. Here’s why:

ArrayList and Vector both use an array to store the elements of the list. 

As a result, when an element is inserted into (or removed from) the middle of the list, the elements that follow must all be shifted accordingly. 

Vector is synchronized, so if a thread-safe implementation is not needed, it is recommended to use ArrayList rather than Vector. So as the stack, which extends the Vector.

LinkedList, on the other hand, is implemented using a doubly linked list. 
As a result, an inserting or removing an element only requires updating the links with only O(1) cost.


# Arraylist vs Vector

1.  Synchronization and Thread-Safe

    Vector is synchronized while ArrayList is not.
    In Vector class all the methods are synchronized.

2.  Performance

    Vector is slow as it is thread safe.

3. Automatic Increase in Capacity

    A Vector defaults to doubling size of its array. 
    While ArrayList increases its Array size by 50%. new = old*3/2+1

    By default ArrayList size is 10 . It checks whether it reaches the last  element then it will create the new array, copy the new data of last array to new array, then old array is garbage collected by the Java Virtual Machine (JVM) . 

4. Set Increment Size

    ArrayList does not define the increment size . Vector defines the increment size .
    ```
    public synchronized void setSize(int i) { //some code  }
    ```

5. Enumerator

    Other than Hashtable, Vector is the only other class which uses both Enumeration and Iterator.


