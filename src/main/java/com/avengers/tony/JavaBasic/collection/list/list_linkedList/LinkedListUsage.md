## LinkedList

```
LinkedList<String> linkedList = new LinkedList<>();
linkedList.add("a");
linkedList.add("b");
linkedList.add("c");
linkedList.add("d");
linkedList.add("e");           // [a, b, c, d, e]
```

## add and set

```
// add allow index==size
linkedList.add(4,"e");         // [a, b, c, d, e]
linkedList.add(9,"f");         // java.lang.IndexOutOfBoundsException

// set allow index<size
linkedList.set(1,"f");         // bad performance, avoid this
linkedList.set(9,"f");         // java.lang.IndexOutOfBoundsException
```

## Operation on First/Last element

|               | Exception     | success | fail      |
|:------------- |:--------------|:--------|:----------|
| get(first)    | getFirst()    | e       | Exception |
|               | peekFirst()   | e       | null      |
| remove(first) | removeFirst() | e       | Exception |
|               | pollFirst()   | e       | null      |
| add(first)    | addFirst(e)   | void    | Exception |
|               | offerFirst(e) | true    | false     |


|               | Exception     | success | fail      |
|:------------- |:--------------|:--------|:----------|
| get(last)     | getLast()     | e       | Exception |
|               | peekLast()    | e       | null      |
| remove(last)  | removeLast()  | e       | Exception |
|               | pollLast()    | e       | null      |
| add(last)     | addLast(e)    | void    | Exception |
|               | offerLast(e)  | true    | false     |

## toArray

```
String[] arr = (String[])linkedList.toArray(new String[linkedList.size()]);
```

## clear and isEmpty

```
linkedList.clear();         // void
linkedList.isEmpty()        // true
```

## As Stack - LIFO

```
LinkedList<String> stack = new LinkedList<>();
stack.push("1");
stack.push("2");
stack.push("3");        // void

stack.pop()             // e or NoSuchElementException(when stack is empty)
stack.peek()            // e or null(when stack is empty)

| stack way | equivalent way |
|:----------|:---------------|
| push(e)   | addFirst(e)    |
| pop()     | removeFirst()  |
| peek()    | peekFirst()    |
```

## As Queue - FIFO

```
LinkedList<String> queue = new LinkedList<>();
queue.add("1");
queue.add("2");
queue.add("3");         // true

queue.remove();         // e or NoSuchElementException(when stack is empty)
queue.element();        // e or NoSuchElementException(when stack is empty)

| queue way | equivalent way |
|:----------|:---------------|
| add(e)    | addLast(e)     |
| offer(e)  | offerLast(e)   |
| remove()  | removeFirst()  |
| poll()    | pollFirst()    |
| element() | getFirst()     |
| peek()    | peekFirst()    |
```

## Traversal

```
// 1. Iterator(best)
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String e = iterator.next();
}

// 2. ForEach(is equivalent to Iterator, but can not remove element or replace)
for (String item : list){
    String e = item;
}

// 3. ForLoop(really bad)
for (int i = 0; i < list.size(); i++) {
    String e = list.get(i);
}
```