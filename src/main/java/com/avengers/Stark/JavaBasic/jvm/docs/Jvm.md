# JVM

![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/jvm.webp)

### JRE

JRE由Java API和JVM组成，JVM通过类加载器(Class Loader)加类Java应用，并通过Java API进行执行。

### JVM - 特性

* **基于栈(Stack-based virtual machine)**
    
    不同于Intel x86和ARM等比较流行的计算机处理器都是基于寄存器(register)架构，JVM是基于栈执行的。
    
* **符号引用(Symbolic reference) - 句柄**

    除基本类型外的所有Java类型(类和接口)都是通过符号引用取得关联的，而非显式的基于内存地址的引用。 
    
* **垃圾回收机制(Garbage collection)**

    类的实例通过用户代码进行显式创建，但却通过垃圾回收机制自动销毁。
    
* **平台无关性(Guarantees platform independence by clearly defining the primitive data type)**

    像C/C++等传统编程语言对于int类型数据在同平台上会有不同的字节长度。JVM却通过明确的定义基本类型的字节长度来维持代码的平台兼容性，从而做到平台无关。

* **网络字节序(Network byte order)**

    Java class文件的二进制表示使用的是基于网络的字节序(network byte order)。为了在使用小端(little endian)的Intel x86平台和在使用了大端(big endian)的RISC系列平台之间保持平台无关，必须要定义一个固定的字节序。JVM选择了网络传输协议中使用的网络字节序，即基于大端(big endian)的字节序。

### JVM - 结构

![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/runTimeDataArea.png)

* **PC寄存器(PC register)**

    每个线程都会有一个PC(Program Counter)寄存器，并跟随线程的启动而创建。PC寄存器中存有将执行的JVM指令的地址。
    
* **JVM栈(JVM stack)**

    每个线程都有一个JVM栈，并跟随线程的启动而创建。其中存储的数据无素称为栈帧(Stack Frame)。JVM会每把栈桢压入JVM栈或从中弹出一个栈帧。如果有任何异常抛出，像printStackTrace()方法输出的栈跟踪信息的每一行表示一个栈帧。 

    * **栈帧(Stack frame)**:在JVM中一旦有方法执行，JVM就会为之创建一个栈帧，并把其添加到当前线程的JVM栈中。当方法运行结束时，栈帧也会相应的从JVM栈中移除。栈帧中存放着对**本地变量数组**、**操作数栈**以及属于当前运行方法的运行时**常量池的引用**。本地变量数组和操作数栈的大小在编译时就已确定，所以属在运行时属于方法的栈帧大小是固定的。
        
        * **本地变量数组**：本地变量数组的索引从0开始计数，其位置存储着对方法所属类实例的引用。从索引位置1开始的保存的是传递给该方法的参数。其后存储的就是真正的方法的本地变量了。
        * **操作数栈**：是方法的实际运行空间。每个方法变换操作数栈和本地变量数组，并把调用其它方法的结果从栈中弹或压入。在编译时，编译器就能计算出操作数栈所需的内存窨，因此操作数栈的大小在编译时也是确定的。

    ![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/javaStack.png)
    
* **本地方法栈(Native method stack)**

    为非Java编写的本地代程定义的栈空间。也就是说它基本上是用于通过JNI(Java Native Interface)方式调用和执行的C/C++代码。根据具体情况，C栈或C++栈将会被创建。
    
    * -Xss the stack size for each thread
    * -Xss determines the size of the stack: –Xss1024k.

* **方法区(Method area)**

    方法区是被所有线程共用的内存空间，在JVM启动时创建。它存储了运行时常量池、字段和方法信息、静态变量以及被JVM载入的所有类和接口的方法的字节码。不同的JVM提供者在实现方法区时会通常有不同的形式。在Oracle的Hotspot JVM里方法区被称为Permanent Area(永久区)或Permanent Generation(PermGen， 永久代)。JVM规范并对方法区的垃圾回收未做强制限定，因此对于JVM实现者来说，方法区的垃圾回收是可选操作。

    * **运行时常量池**
    
        一个存储了类文件格式中的常量池表的内存空间。这部分空间虽然存在于方法区内，但却在JVM操作中扮演着举足轻重的角色，因此JVM规范单独把这一部分拿出来描述。除了每个类或接口中定义的常量，它还包含了所有对方法和字段的引用。因此当需要一个方法或字段时，JVM通过运行时常量池中的信息从内存空间中来查找其相应的实际地址。

* **数据堆(Heap)**

    堆中存储着所有的类实例或对象，并且也是垃圾回收的目标场所。当涉及到JVM性能优化时，通常也会提及到数据堆空间的大小设置。JVM提供者可以决定划分堆空间或者不执行垃圾回收。
    
    * -Xms	initial java heap size
    * -Xmx	maximum java heap size
    * -Xmn	the size of the heap for the young generation

### JVM - 类加载器(ClassLoader)

* **代理模型(Class Loader Delegation Model)**

    加载器会先从缓存中判断此类是否已存在，如果不存在就请示父类加载器判断是否存在，如果直到Bootstrap类加载器都不存在该类，那么当前类加载器就会从文件系统中找到类文件进行加载。
    
    * **Bootstrap class loader**
        
        Bootstrap加载器在运行JVM时创建，用于加载Java APIs，包括Object类。不像其他的类加载器由Java代码实现，Bootstrap加载器是由native代码实现的。
        
    * **Extension class loader**
    
        扩展加载器用于加载除基本Java APIs以外扩展类。也用于加载各种安全扩展功能。
        
    * **System class loader**
    
        如果说Bootstrap和Extension加载器用于加载JVM运行时组件，那么系统加载器加载的则是应用程序相关的类。它会加载用户指定的CLASSPATH里的类。
    
    * **User-defined class loader**
        
        这个是由用户的程序代码创建的类加载器。

    ![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/classloader.png)
    
* **类加载步骤**

    * **加载(Loading)**
        
        从文件中获取类并载入到JVM内存空间。
    
    * **验证(Verifying)**
        
        验证载入的类是否符合Java语言规范和JVM规范。在类加载流程的测试过程中，这一步是最为复杂且耗时最长的部分。大部分JVM TCK的测试用例都用于检测对于给定的错误的类文件是否能得到相应的验证错误信息。
    
    * **准备(Preparing)**
    
        根据内存需求准备相应的数据结构，并分别描述出类中定义的字段、方法以及实现的接口信息。
    
    * **解析(Resolving)**
    
        把类常量池中所有的符号引用转为直接引用。
    
    * **初始化(Initializing)**
    
        为类的变量初始化合适的值。执行静态初始化域，并为静态字段初始化相应的值。
    
    ![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/classLoadStage.png)
    
    
### **执行引擎(Execution Engine)**

![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/javaCompiler.png)

* 解释器(Interpreter)

    读取、解释并逐一执行每一条字节码指令。因为解释器逐一解释和执行指令，因此它能够快速的解释每一个字节码，但对解释结果的执行速度较慢。所有的解释性语言都有类似的缺点。叫做字节码的语言人本质上就像一个解释器一样运行。

* 即时编译器(JIT: Just-In-Time)

    即时编译器的引入用来弥补解释器的不足。执行引擎先以解释器的方式运行，然后在合适的时机，即时编译器把整修字节码编译成本地代码。然后执行引擎就不再解释方法的执行而是通过使用本地代码直接执行。执行本地代码较逐一解释执行每条指令在速度上有较大的提升，并且通过对本地代码的缓存，编译后的代码能具有更快的执行速度。

    ![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/jitCompiler.png)

###### **References**

* [深入理解JVM](https://www.cnblogs.com/enjiex/p/5079338.html)
* [Understanding JVM Internals](https://www.cubrid.org/blog/understanding-jvm-internals)