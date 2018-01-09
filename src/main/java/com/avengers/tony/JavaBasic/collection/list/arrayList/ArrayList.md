# ArrayList

### Basic

* Resizable-array extends *AbstractList<E>* implements *List<E>*.
* Permits NULLs.
* Provides constant-time performance for size(), isEmpty(), get(), set(), iterator(), ListIterator().
* add(), remove() operations runs in amortized constant time O(n).
* Capacity >= Size, default capacity is 10.
* Size: 1.5 times
    ```
    initialCapacity = 10;
    newCapacity = oldCapacity + (oldCapacity >> 1);
    newCapacity >= minCapacity? newCapacity
                              : minCapacity
    ```
* Use fail-fast iterators.
* No thread safe.
    ```
    List list = Collections.synchronizedList(new ArrayList(...));
    ```

### Source

* Data Structure

    ```
    transient Object[] elementData
    ```
    
* DEFAULT_CAPACITY 

    ```
    private static final int DEFAULT_CAPACITY = 10;
    ```

* Constructor

    ```
    // 1. DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {}
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    
    // 2. 
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
    
    // 3.
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
    ```
    
* About capacity and size

    An application can increase the capacity of an ArrayList instance 
    before adding a large number of elements using the ensureCapacity
    operation.  <br>
    This may reduce the amount of incremental reallocation.

    ```
    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            ? 0 : DEFAULT_CAPACITY;
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    // minCapacity > 10? minCapacity : 10
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    
    // 1.5 times
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
    ```
    
* Basic Operations

    ```
    // add      -> modCount++
    public boolean add(E e)
    public void add(int index, E element)
    public boolean addAll(Collection<? extends E> c)
    public boolean addAll(int index, Collection<? extends E> c)
    
    // remove   -> modCount++
    public E remove(int index)
    public boolean remove(Object o)
    public boolean removeAll(Collection<?> c) 
    public boolean retainAll(Collection<?> c)
    public void clear() 
    
    // update
    public E set(int index, E element) 
    
    // query
    ublic int size()
    public boolean isEmpty()
    public boolean contains(Object o)
    public int indexOf(Object o)        //first occurrence
    public int lastIndexOf(Object o)    //last occurrence
    public E get(int index)
    public List<E> subList(int fromIndex, int toIndex)
    ```
    
* About Fail-fast

    if the list is structurally modified at any time after the iterator is created,<br> 
    in any way except through the iterator's own remove() or add() methods, <br> 
    the iterator will throw a ConcurrentModificationException. <br> 
    The iterator fails quickly and cleanly, <br>
    rather than risking arbitrary, non-deterministic behavior <br>
    at an undetermined time in the future. <br>

* Iterator

    ```
    /**
     * Itr  ->  hasNext(): boolean
     *          next(): E
     *          remove(): void
     */
    public Iterator<E> iterator()
        
    /**
     * ListItr  ->  hasNext(): boolean
     *              nextIndex(): int
     *              next(): E
     *              hasPrevious(): boolean
     *              previousIndex(): int
     *              previous(): E
     *              set(E e): void
     *              add(E e): void
     *              remove(): void
     */
    public ListIterator<E> listIterator(int index)
    public ListIterator<E> listIterator() 
    ```
    
* New API

    ```
    /**
     * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
     * elements themselves are not copied.)
     *
     * @return a clone of this <tt>ArrayList</tt> instance
     */
    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }
    
    /**
     * Trims the capacity of this <tt>ArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
              ? EMPTY_ELEMENTDATA
              : Arrays.copyOf(elementData, size);
        }
    }
    
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                         numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }
    ```
    
### Usage Demo

[CODE]()