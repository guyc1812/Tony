



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


Difference between ArrayList and LinkedList in Java 

1. Implementation :  ArrayList is the resizable array implementation of list interface , while LinkedList is the Doubly-linked list implementation of the list interface.


2. Performance  :  Performance of ArrayList and LinkedList depends on the type of operation

a. get(int index) or search operation :  ArrayList get(int index) operation runs in constant time i.e O(1)  while LinkedList get(int index) operation run time is O(n) .

The reason behind ArrayList being faster than LinkedList is that ArrayList uses index based system for its elements as it internally uses array data structure , on the other hand ,
LinkedList does not provide index based access for its elements as it iterates either from the beginning or end (whichever is closer) to retrieve the node at the specified element index.

b. insert() or add(Object) operation :  Insertions in LinkedList are generally fast as compare to ArrayList.

In LinkedList adding or insertion is O(1) operation . While in ArrayList, if array is full i.e worst case,  there is extra cost of  resizing array and copying elements to the new array , which makes runtime of add operation in ArrayList O(n) , otherwise it is O(1) .

c. remove(int) operation :  Remove operation in LinkedList is generally same as ArrayList i.e. O(n).
In LinkedList , there are two overloaded remove methods. one is remove() without any parameter which removes the head of the list and runs in constant time O(1) .
The other overloaded remove method in LinkedList is remove(int) or remove(Object) which removes the Object or int passed as parameter . This method traverses the LinkedList until it found the Object and unlink it from the original list . Hence this method run time is O(n).

While in ArrayList remove(int) method involves copying elements from old array to new updated array , hence its run time is O(n).

3.  Reverse  Iterator :  LinkedList can be iterated in reverse direction using descendingIterator() while there is no descendingIterator() in ArrayList , so we need to write our own code to iterate over the ArrayList in reverse direction.

4. Initial Capacity :  If the constructor  is not overloaded , then ArrayList creates an empty list of initial capacity 10 , while LinkedList  only constructs the empty list without any initial capacity.

5. Memory Overhead :  Memory overhead in LinkedList is more as compared to ArrayList as node in LinkedList needs to maintain the addresses of next and previous node. While in ArrayList  each index only holds the actual object(data).