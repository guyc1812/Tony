package com.avengers.tony.JavaBasic.collection.list.list_linkedList.code;

import java.util.Arrays;
import java.util.LinkedList;

public class ListDemo {

    /**
     * 测试LinkedList中部分API
     */
    private static void testAPIs() {

        LinkedList linkedList = new LinkedList();

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        System.out.println("Init the linkedList");
        System.out.println("linkedList:" + linkedList);

        // add “9” at index=0
        linkedList.add(0, "9");
        System.out.println("\nlinkedList.add(0, \"9\")");
        System.out.println("linkedList:" + linkedList);

        linkedList.set(0, "0");
        System.out.println("\nlinkedList.set(0,\"0\") => avoid this");
        System.out.println("linkedList:" + linkedList);


        System.out.println("\nTest \"addFirst(), removeFirst(), getFirst()  ifFailed => Exception\"");
        // (01) 将“10”添加到第一个位置。  失败的话，抛出异常！
        linkedList.addFirst("10");
        System.out.println("linkedList.addFirst(\"10\") => void");
        System.out.println("linkedList:" + linkedList);
        // (02) 将第一个元素删除。        失败的话，抛出异常！
        System.out.println("linkedList.removeFirst():" + linkedList.removeFirst());
        System.out.println("linkedList:" + linkedList);
        // (03) 获取第一个元素。          失败的话，抛出异常！
        System.out.println("linkedList.getFirst():" + linkedList.getFirst());
        System.out.println("linkedList:" + linkedList);


        System.out.println("\nTest \"offerFirst(), pollFirst(), peekFirst() ifFailed => null\"");
        // (01) 将“10”添加到第一个位置。  返回true。
        linkedList.offerFirst("10");
        System.out.println("linkedList.offerFirst(\"10\") => true");
        System.out.println("linkedList:" + linkedList);
        // (02) 将第一个元素删除。        失败的话，返回null。
        System.out.println("linkedList.pollFirst():" + linkedList.pollFirst());
        System.out.println("linkedList:" + linkedList);
        // (03) 获取第一个元素。          失败的话，返回null。
        System.out.println("linkedList.peekFirst():" + linkedList.peekFirst());
        System.out.println("linkedList:" + linkedList);


        System.out.println("\nTest \"addLast(), removeLast(), getLast()\"");
        // (01) 将“20”添加到最后一个位置。  失败的话，抛出异常！
        linkedList.addLast("20");
        System.out.println("linkedList.addLast(\"20\") => void");
        System.out.println("linkedList:" + linkedList);
        // (02) 将最后一个元素删除。        失败的话，抛出异常！
        System.out.println("linkedList.removeLast():" + linkedList.removeLast());
        System.out.println("linkedList:" + linkedList);
        // (03) 获取最后一个元素。          失败的话，抛出异常！
        System.out.println("linkedList.getLast():" + linkedList.getLast());
        System.out.println("linkedList:" + linkedList);


        System.out.println("\nTest \"offerLast(), pollLast(), peekLast()\"");
        // (01) 将“20”添加到第一个位置。  返回true。
        linkedList.offerLast("20");
        System.out.println("linkedList.offerLast(\"20\") => true");
        System.out.println("linkedList:" + linkedList);
        // (02) 将第一个元素删除。        失败的话，返回null。
        System.out.println("linkedList.pollLast():" + linkedList.pollLast());
        System.out.println("linkedList:" + linkedList);
        // (03) 获取第一个元素。          失败的话，返回null。
        System.out.println("linkedList.peekLast():" + linkedList.peekLast());
        System.out.println("linkedList:" + linkedList);


        // ---- toArray(T[] a) ----
        System.out.println("\nTest \"toArray(T[] a)\"");
        String[] arr = (String[]) linkedList.toArray(new String[linkedList.size()]);
        System.out.println(Arrays.toString(arr));


        linkedList.clear();
        System.out.println("\nlinkedList.clear()");
        System.out.println("linkedList:" + linkedList);
        System.out.println("linkedList.isEmpty():" + linkedList.isEmpty() + "\n");

    }

    /**
     * 将LinkedList当作 LIFO(后进先出)的堆栈
     */
    private static void asStack() {
        System.out.println("\nStack");

        // 新建一个LinkedList
        LinkedList stack = new LinkedList();

        // 将1,2,3,4添加到堆栈中
        stack.push("1");
        stack.push("2");
        stack.push("3");

        // 打印“栈”
        System.out.println("stack:" + stack);

        // 添加“栈顶元素”
        stack.push("4");
        System.out.println("stack.push(\"4\") => void");
        System.out.println("stack:" + stack);

        // 删除“栈顶元素”
        System.out.println("stack.pop():" + stack.pop());
        System.out.println("stack:" + stack);

        // 取出“栈顶元素”
        System.out.println("stack.peek():" + stack.peek());
        System.out.println("stack:" + stack);

    }

    /**
     * 将LinkedList当作 FIFO(先进先出)的队列
     */
    private static void asQueue() {
        System.out.println("\nQueue");
        // 新建一个LinkedList
        LinkedList queue = new LinkedList();

        // 将10,20,30,40添加到队列。每次都是插入到末尾
        queue.add("10");
        queue.add("20");
        queue.add("30");

        // 打印“队列”
        System.out.println("queue:" + queue);

        // 插入(队尾)
        System.out.println("queue.add(\"40\") => " + queue.add("40"));
        System.out.println("queue:" + queue);

        // 删除(队列的第一个元素)
        System.out.println("queue.remove():" + queue.remove());
        System.out.println("queue:" + queue);

        // 读取(队列的第一个元素)
        System.out.println("queue.element():" + queue.element());
        System.out.println("queue:" + queue);

    }


    public static void main(String[] args) {
        testAPIs();
        asStack();
        asQueue();
    }


}
