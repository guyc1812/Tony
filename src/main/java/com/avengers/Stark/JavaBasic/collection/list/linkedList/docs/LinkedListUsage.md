# LinkedList Usage

### new LinkedList

```
LinkedList<String> linkedList = new LinkedList<>();
linkedList.add("a");
linkedList.add("b");
linkedList.add("c");
linkedList.add("d");
linkedList.add("e");           // [a, b, c, d, e]
```

### add and set

```
// add allow index==size
linkedList.add(4,"e");         // [a, b, c, d, e]
linkedList.add(9,"f");         // java.lang.IndexOutOfBoundsException

// set allow index<size
linkedList.set(1,"f");         // bad performance, avoid this
linkedList.set(9,"f");         // java.lang.IndexOutOfBoundsException
```

### Operation on First/Last element

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

### toArray

```
String[] arr = (String[])linkedList.toArray(new String[linkedList.size()]);
```

### clear and isEmpty

```
linkedList.clear();         // void
linkedList.isEmpty()        // true
```

### As Stack - LIFO

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

### As Queue - FIFO

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


### Usage Demo

* basicApi

    ```
    private static void basicApi() {

        LinkedList linkedList = new LinkedList();

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        // add "9" at index=0
        linkedList.add(0, "9");         // [9, 1, 2, 3]

        // set "0" at index=0 => avoid this
        linkedList.set(0, "0");         // [0, 1, 2, 3]

        // First -> add, remove, get -> ifFailed: Exception
        linkedList.addFirst("10");      // return void, [10, 0, 1, 2, 3]
        linkedList.removeFirst();       // return 10, [0, 1, 2, 3]
        linkedList.getFirst();          // return 0, [0, 1, 2, 3]

        // First -> offer, poll, peek -> ifFailed: null
        linkedList.offerFirst("10");    // return true, [10, 0, 1, 2, 3]
        linkedList.pollFirst();         // return 10, [0, 1, 2, 3]
        linkedList.peekFirst();         // return 0, [0, 1, 2, 3]

        // Last -> add, remove, get -> ifFailed: Exception
        linkedList.addLast("20");       // return void, [0, 1, 2, 3, 20]
        linkedList.removeLast();        // return 20, [0, 1, 2, 3]
        linkedList.getLast();           // return 3, [0, 1, 2, 3]

        // Last -> offer, poll, peek -> ifFailed: null
        linkedList.offerLast("20");     // return true, [0, 1, 2, 3, 20]
        linkedList.pollLast();          // return 20, [0, 1, 2, 3]
        linkedList.peekLast();          // return 3, [0, 1, 2, 3]

        // toArray(T[] a)
        String[] arr = (String[]) linkedList.toArray(new String[linkedList.size()]);
        // [0, 1, 2, 3]

        linkedList.clear();
    }
    ```

* stackApi

    ```
    private static void stackApi() {

        LinkedList stack = new LinkedList();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        stack.push("4");    // return void, [4, 3, 2, 1]
        stack.pop();        // return 4, [3, 2, 1]
        stack.peek();       // return 3, [3, 2, 1]

    }
    ```

* queueApi

    ```
    private static void queueApi() {

        LinkedList queue = new LinkedList();

        queue.add("1");
        queue.add("2");
        queue.add("3");

        queue.add("4");    // return true, [1, 2, 3, 4]
        queue.remove();     // return 1, [2, 3, 4]
        queue.element();    // return 2, [2, 3, 4]

    }
    ```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/collection/list/linkedList/code)