* set

    * Set 是继承于Collection的接口。它是一个不允许有重复元素的集合。
    * AbstractSet 是一个抽象类，它继承于AbstractCollection，AbstractCollection实现了Set中的绝大部分函数，为Set的实现类提供了便利。
    * HastSet 和 TreeSet 是Set的两个实现类。
        * HashSet依赖于HashMap，它实际上是通过HashMap实现的。HashSet中的元素是无序的。
        * TreeSet依赖于TreeMap，它实际上是通过TreeMap实现的。TreeSet中的元素是有序的。

# If one needs a Set, how do you choose between HashSet vs. TreeSet?

At first glance, HashSet is superior in almost every way: O(1) add, remove and contains, vs. O(log(N)) for TreeSet.

However, TreeSet is indispensable when you wish to maintain order over the inserted elements or query for a range of elements within the set.

Consider a Set of timestamped Event objects. They could be stored in a HashSet, with equals and hashCode based on that timestamp. This is efficient storage and permits looking up events by a specific timestamp, but how would you get all events that happened on any given day? That would require a O(n) traversal of the HashSet, but it’s only a O(log(n)) operation with TreeSet using the tailSet method:
```
        public class Event implements Comparable<Event> {
            private final long timestamp;
    
            public Event(long timestamp) {
                this.timestamp = timestamp;
            }
    
            @Override public int compareTo(Event that) {
                return Long.compare(this.timestamp, that.timestamp);
            }
        }
       
        ...
	
        SortedSet<Event> events = new TreeSet<>();
        events.addAll(...); // events come in

        // all events that happened today
        long midnightToday = ...;
        events.tailSet(new Event(midnightToday));
```
If Event happens to be a class that we cannot extend or that doesn’t implement Comparable, TreeSet allows us to pass in our own Comparator:
```
        SortedSet<Event> events = new TreeSet<>(
                (left, right) -> Long.compare(left.timestamp, right.timestamp));
```
Generally speaking, TreeSet is a good choice when order matters and when reads are balanced against the increased cost of writes.


# and or xor