# JVM

![image](http://guyuchen.com/deadpool/github/Java-JVM-Tuning-Crunchify-Tips.png)

### Heap Size:

When a Java program starts, Java Virtual Machine gets some memory from Operating System. <br>
Java Virtual Machine uses this memory for all its need and part of this memory is call java heap memory. <br>
Whenever we create object using new operator or by any another means, <br>
object is allocated memory from Heap and When object dies or garbage collected, <br>
memory goes back to Heap space in Java.

![image](http://guyuchen.com/deadpool/github/jvm.png)

JVM option Meaning<br>
* -Xms	initial java heap size
* -Xmx	maximum java heap size
* -Xmn	the size of the heap for the young generation

It is good practice for big production projects to set the minimum -Xms and maximum -Xmx heap sizes to the same value.

For efficient garbage collection, the -Xmn value should be lower than the -Xmx value. <br>
Heap size does not determine the amount of memory your process uses.

If you monitor your java process with an OS tool like top or task manager, <br>
you may see the amount of memory you use exceed the amount you have specified for -Xmx. <br>
-Xmx limits the java heap size, java will allocate memory for other things, including a stack for each thread. <br>
It is not unusual for the total memory consumption of the VM to exceed the value of -Xmx.


### Stack Size:

Each thread in the VM get’s a stack. <br>
The stack size will limit the number of threads that you can have, <br>
too big of a stack size and you will run out of memory as each thread is allocated more memory than it needs.

JVM option Meaning<br>
* -Xss the stack size for each thread
* -Xss determines the size of the stack: –Xss1024k. 

If the stack space is too small, eventually you will see an exception class java.lang.StackOverflowError.


### Garbage Collection:

There are essentially two GC threads running. 

One is a very lightweight thread which does "little" collections primarily on the Young generation of the heap. <br>
The other is the Full GC thread which traverses the entire heap <br>
when there is not enough memory left to allocate space for objects <br>
which get promoted from the Young to the older generation(s).<br>

If there is a memory leak or inadequate heap allocated, <br>
eventually the older generation will start to run out of room causing the Full GC thread to run (nearly) continuously. 
Since this process stops the world, <br>
Java application won’t be able to respond to requests and they’ll start to back up or OOM.

The amount allocated for the Young(Eden) generation is the value specified with -Xmn. <br>
The amount allocated for the older generation is the value of -Xmx minus the -Xmn. <br>
Generally, you don’t want the Eden to be too big or it will take too long for the GC <br>
to look through it for space that can be reclaimed.


### Class Loader

The Java Classloader is the part of the Java runtime environment <br>
that loads classes on demand (lazy loading) into the JVM (Java Virtual Machine). <br>
Classes may be loaded from the local file system, a remote file system, or even the web.<br>

When the JVM is started, three class loaders are used: <br>
* Bootstrap Classloader: Loads core java API file rt.jar from folder. 
* Extension Classloader: Loads jar files from folder. 
* System/Application Classloader: Loads jar files from path specified in the CLASSPATH environment variable.
