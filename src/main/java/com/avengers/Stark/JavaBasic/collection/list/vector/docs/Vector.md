# Vector

### Basic

* Resizable-array extends *AbstractList<E>* implements *List<E>*.
* Permits NULLs.
* Provides constant-time performance for size(), isEmpty(), get(), set(), iterator(), ListIterator(), Enumeration.
* add(), remove() operations runs in amortized constant time O(n).
* Capacity >= Size, default capacity is 10.
* Size: 2 times
    ```
    initialCapacity = 10 ;
    capacityIncrement? newCapacity = oldCapacity + capacityIncrement
                   : newCapacity = oldCapacity * 2 ;
    ```
* Use fail-fast iterators.
* Use non-fail-fast Enumeration.
* Thread Safe (wrapped by Synchronized).


### Source

* Data Structure

    ```
    protected Object[] elementData;
    ```
    
* DEFAULT_CAPACITY 

    ```
    initialCapacity = 10;
    ```

* Constructor

    ```
    public Vector(int initialCapacity) {
        this(initialCapacity, 0);
    }
    
    public Vector(int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    public Vector() {
        this(10);
    }

    public Vector(Collection<? extends E> c) {
        elementData = c.toArray();
        elementCount = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
    }
    ```
    
* About capacity and size

    ```
    initialCapacity = 10 ;
    capacityIncrement? newCapacity = oldCapacity + capacityIncrement
                   : newCapacity = oldCapacity * 2 ;
    ```
    
    ```
    public synchronized void ensureCapacity(int minCapacity) {
        if (minCapacity > 0) {
            modCount++;
            ensureCapacityHelper(minCapacity);
        }
    }
    
    private void ensureCapacityHelper(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    
    // 2 times
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                         capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
    ```

* APIs

    ```
    // query
    synchronized int            size()
    synchronized int            capacity()
    synchronized boolean        isEmpty()
                 
                 boolean        contains(Object object)
    synchronized boolean        containsAll(Collection<?> collection)
                 
    synchronized int            indexOf(Object object, int location)
                 int            indexOf(Object object)
    synchronized int            lastIndexOf(Object object, int location)
    synchronized int            lastIndexOf(Object object)
                 
                 E              get(int location)
    synchronized E              lastElement()
    
                 Enumeration<E> elements()
    synchronized E              elementAt(int location)
    synchronized E              firstElement()
                 
    synchronized List<E>        subList(int start, int end)
    
    // add
    synchronized boolean        add(E object)
                 void           add(int location, E object)
    synchronized boolean        addAll(Collection<? extends E> collection)
    synchronized boolean        addAll(int location, Collection<? extends E> collection)
    synchronized void           addElement(E object)
    
    // remove
                 void           clear()
    synchronized E              remove(int location)
                 boolean        remove(Object object)
    synchronized boolean        removeAll(Collection<?> collection)
    synchronized void           removeAllElements()
    synchronized boolean        removeElement(Object object)
    synchronized void           removeElementAt(int location)
    
    synchronized boolean        retainAll(Collection<?> collection)
    
    // modify
    synchronized void           insertElementAt(E object, int location)
    synchronized E              set(int location, E object)
    synchronized void           setElementAt(E object, int location)
    synchronized void           setSize(int length)
    synchronized void           trimToSize()
    
    // hash
    synchronized int            hashCode()
    synchronized boolean        equals(Object object)
    synchronized Object         clone()
    ```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/list/vector/code)
