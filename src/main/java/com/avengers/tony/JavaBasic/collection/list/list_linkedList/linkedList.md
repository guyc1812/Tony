# LinkedList

Doubly-linked list implementation of the List and Deque interfaces

permit NULL

Operations that index into the list will traverse the list from the beginning or the end, 
whichever is closer to the specified index.

```
List list = Collections.synchronizedList(new LinkedList(...));
```


# Data Structure

```
transient Node<E> first;
transient Node<E> last;
```

# Constructor

```
public LinkedList() {}

public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}
```

# Usage

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

# Iterator

```
public Iterator<E> descendingIterator()
```