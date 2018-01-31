# LinkedList

* Doubly-linked list extends *AbstractSequentialList<E>*.  <br>
    implements *List<E>*, *Deque<E>*
* Permits NULLs.
* Operations that index into the list will traverse the list from the beginning or the end, <br>
    whichever is closer to the specified index.
* Provides constant-time performance for size(), isEmpty(), add(), remove().
* get(), set(), iterator(), ListIterator() operation runs in amortized constant time O(n).
* Use fail-fast iterators.
* No thread safe.
```
List list = Collections.synchronizedList(new LinkedList(...));
```

### Source

* Data Structure

    ```
    transient Node<E> first;
    transient Node<E> last;
    ```

* Constructor

    ```
    public LinkedList() {}
    
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    ```

* Basic Operations

    ```
    public int size()
    public boolean contains(Object o)
    
    public E getFirst()
    public E getLast()
    public E removeFirst()
    public E removeLast()
    public void addFirst(E e)
    public void addLast(E e)
    
    public boolean add(E e) //equivalent to addLast()
    public boolean remove(Object o)  
    public void clear()
    
    public boolean addAll(Collection<? extends E> c)
    public boolean addAll(int index, Collection<? extends E> c)
    
    //Positional Access Operations
    
    public E get(int index) 
    public E set(int index, E element)
    public void add(int index, E element)
    public E remove(int index) 
    
    public int indexOf(Object o)
    public int lastIndexOf(Object o)
    
    // Queue operations.
    
    public E peek()
    public E element()
    public E poll()
    ublic E remove() 
    public boolean offer(E e)
    
    // Deque operations
    
    public boolean offerFirst(E e)
    public boolean offerLast(E e) 
    public E peekFirst() 
    public E peekLast()
    public E pollFirst()
    public E pollLast() 
    public void push(E e)
    public E pop()
    public boolean removeFirstOccurrence(Object o)
    public boolean removeLastOccurrence(Object o)
    ```

* Iterator

    ```
    public ListIterator<E> listIterator(int index)
    public Iterator<E> descendingIterator()
    ```
    
    
### Crucial operations

```
/**
 * Links e as first element.
 */
private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null)
        last = newNode;
    else
        f.prev = newNode;
    size++;
    modCount++;
}

/**
 * Links e as last element.
 */
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}

/**
 * Inserts element e before non-null Node succ.
 */
void linkBefore(E e, Node<E> succ) {
    // assert succ != null;
    final Node<E> pred = succ.prev;
    final Node<E> newNode = new Node<>(pred, e, succ);
    succ.prev = newNode;
    if (pred == null)
        first = newNode;
    else
        pred.next = newNode;
    size++;
    modCount++;
}

/**
 * Unlinks non-null first node f.
 */
private E unlinkFirst(Node<E> f) {
    // assert f == first && f != null;
    final E element = f.item;
    final Node<E> next = f.next;
    f.item = null;
    f.next = null; // help GC
    first = next;
    if (next == null)
        last = null;
    else
        next.prev = null;
    size--;
    modCount++;
    return element;
}

/**
 * Unlinks non-null last node l.
 */
private E unlinkLast(Node<E> l) {
    // assert l == last && l != null;
    final E element = l.item;
    final Node<E> prev = l.prev;
    l.item = null;
    l.prev = null; // help GC
    last = prev;
    if (prev == null)
        first = null;
    else
        prev.next = null;
    size--;
    modCount++;
    return element;
}

/**
 * Unlinks non-null node x.
 */
E unlink(Node<E> x) {
    // assert x != null;
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
        first = next;
    } else {
        prev.next = next;
        x.prev = null;
    }

    if (next == null) {
        last = prev;
    } else {
        next.prev = prev;
        x.next = null;
    }

    x.item = null;
    size--;
    modCount++;
    return element;
}
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/list/linkedList/code)
