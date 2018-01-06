# Collection & List

### package java.util


* CURD Operations

    ```
    int size();
    
    boolean isEmpty();
    
    boolean contains(Object o);
    boolean containsAll(Collection<?> c);

    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
    
    boolean retainAll(Collection<?> c);
   
    boolean remove(Object o);
    boolean removeAll(Collection<?> c);
    
    void clear();
    
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }
    ```


* ToArray Operations

    ```
    Object[] toArray();
    
    <T> T[] toArray(T[] a);
    ```


* Comparison and hashing

    ```
    boolean equals(Object o);

    int hashCode();
    ```
    
* Stream
    
    ```
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
    ```
    

* Collection extends Iterable<T> 

    ```
    // to be implemented
    Iterator<E> iterator();

    // default implementation in Iterable<T> 
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    // Override implementation in Collection<E>
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }
    ```

* List<E> extends Collection<E>

    ```
    E get(int index);
    E set(int index, E element);

    void add(int index, E element);
    boolean addAll(int index, Collection<? extends E> c);

    E remove(int index);

    int indexOf(Object o);
    int lastIndexOf(Object o);
    
    List<E> subList(int fromIndex, int toIndex);
    
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, Spliterator.ORDERED);
    }
    

    ```