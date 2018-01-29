# List


### Summary

* ArrayList

    Implements *List<E>*. <br>
    Dynamic Array.<br>
    Permits NULLs.<br>
    No Thread Safe.<br>
    Fail-Fast.<br>
    Size: 1.5 times
    ```
    initialCapacity = 10;
    newCapacity = oldCapacity + (oldCapacity >> 1);
    newCapacity >= minCapacity? newCapacity
                              : minCapacity
    ```
    
* LinkedList

    Implements *List<E>*, *Deque<E>*. <br>
    Double List.<br>
    Permits NULLs.<br>
    No Thread Safe.<br>
    Fail-Fast.<br>
    
* Vector 

    Implements *List<E>*. <br>
    Dynamic Array.<br>
    Permits NULLs.<br>
    Thread Safe (wrapped by Synchronized).<br>
    Fail-Fast.<br>
    Supports both Enumeration and Iterator.<br>
    Size: 2 times
    ```
    initialCapacity = 10 ;
    capacityIncrement? newCapacity = oldCapacity + capacityIncrement
                     : newCapacity = oldCapacity * 2 ;
    ```
    
* Stack

    Extends *Vector<E>*.<br>
    Thread Safe.<br>
    FILO, First In Last Out.<br>
    
    
### Performance

* LinkedList is generally provide the best performance. 

    LinkedList is implemented using a doubly linked list. <br>
    As a result, an inserting or removing an element only requires updating the links with only O(1) cost.
      
* ArrayList and Vector both use an array to store the elements of the list. 

    As a result, when an element is inserted into (or removed from) the middle of the list, <br>
    the elements that follow must all be shifted accordingly. 

* Vector is slow as it is thread safe.

    Vector is synchronized, <br>
    so if a thread-safe implementation is not needed, <br>
    it is recommended to use ArrayList rather than Vector. <br>
    So as the stack, which extends the Vector.

    
### ArrayList vs LinkedList

* Implementation

    ArrayList is the resizable array implementation of list interface , <br>
    while LinkedList is the Doubly-linked list implementation of the list interface.
    
* Performance

    * get(int index) or search operation
    
        ArrayList get operation runs in constant time O(1). <br>
        ArrayList uses index based system, can be randomly accessed.<br>
        LinkedList get operation run time is O(n).<br>
        LinkedList iterates either from the beginning or end (whichever is closer) to retrieve the node at the specified element index.
        
    * insert() or add(Object) operation
    
        LinkedList adding or insertion is O(1) operation.
        ArrayList adding or insertion is O(n) operation.
        there is extra cost of  resizing array and copying elements to the new array.
    
    * remove(int) operation
    
        LinkedList remove() runs in constant time O(1).
        LinkedList remove(int) or remove(Object) runs in constant time O(n).
        ArrayList remove(int) method involves copying elements from old array to new updated array , hence its run time is O(n).
    
* Reverse  Iterator

    LinkedList can be iterated by descendingIterator() and descendingIterator().

* Memory Overhead

    LinkedList needs to maintain the addresses of next and previous node. 
    ArrayList each index only holds the actual object(data).

