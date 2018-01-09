# ArrayList, LinkedList, Vector, Stack是List的4个实现类。
    
* ArrayList 是一个数组队列，相当于动态数组。它由数组实现，随机访问效率高，随机插入、随机删除效率低。
* LinkedList 是一个双向链表。它也可以被当作堆栈、队列或双端队列进行操作。LinkedList随机访问效率低，但随机插入、随机删除效率低。
* Vector 是矢量队列，和ArrayList一样，它也是一个动态数组，由数组实现。但是ArrayList是非线程安全的，而Vector是线程安全的。
* Stack 是栈，它继承于Vector。它的特性是：先进后出(FILO, First In Last Out)。

# 使用场景

* 对于需要快速插入，删除元素，应该使用LinkedList。
* 对于需要快速随机访问元素，应该使用ArrayList。
* 对于“多线程环境”，使用Vector或者stack。

# 双向链表查找index位置的节点时，有一个加速动作, 若index < 双向链表长度的1/2，则从前向后查找; 否则，从后向前查找。
    
# Vector和ArrayList比较

   1. 线程安全性不一样：ArrayList是非线程安全, 而Vector是线程安全的。

   2. ArrayList支持序列化，而Vector不支持；即ArrayList有实现java.io.Serializable接口，而Vector没有实现该接口。

   3. ArrayList有3个构造函数，而Vector有4个构造函数。
      Vector除了包括和ArrayList类似的3个构造函数之外，
      另外的一个构造函数可以指定容量增加系数。
      
   4. 容量增加方式不同
   
      逐个添加元素时，若ArrayList容量不足时，“新的容量”=“(原始容量x3)/2 + 1”。
      而Vector的容量增长与“增长系数有关”，若指定了“增长系数”，且“增长系数有效(即，大于0)”；那么，每次容量不足时，“新的容量”=“原始容量+增长系数”。若增长系数无效(即，小于/等于0)，则“新的容量”=“原始容量 x 2”。
      
   5. 对Enumeration的支持不同。Vector支持通过Enumeration去遍历，而List不支持