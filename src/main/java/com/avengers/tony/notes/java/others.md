# What is JVM? Are you aware of Heapsize, Stacksize & Garbage Collection? Please share some more light.

When a Java program starts, Java Virtual Machine gets some memory from Operating System. 
JVM uses this memory for all its need and part of this memory is call java heap memory.

# JVM Tuning: Heapsize, Stacksize and Garbage Collection Fundamental

![image](../../imgs/Java-JVM-Tuning-Crunchify-Tips.png)

1. Heap Size:

When a Java program starts, Java Virtual Machine gets some memory from Operating System. 
Java Virtual Machine uses this memory for all its need and part of this memory is call java heap memory. 
Whenever we create object using new operator or by any another means object is allocated memory from Heap and When object dies or garbage collected, memory goes back to Heap space in Java.

![image](../../imgs/jvm.png)

JVM option	Meaning
-Xms	initial java heap size
-Xmx	maximum java heap size
-Xmn	the size of the heap for the young generation
It is good practice for big production projects to set the minimum -Xms and maximum -Xmx heap sizes to the same value.

For efficient garbage collection, the -Xmn value should be lower than the -Xmx value. 
Heap size does not determine the amount of memory your process uses.

If you monitor your java process with an OS tool like top or task manager, 
you may see the amount of memory you use exceed the amount you have specified for -Xmx. 
-Xmx limits the java heap size, java will allocate memory for other things, including a stack for each thread. 
It is not unusual for the total memory consumption of the VM to exceed the value of -Xmx.

Stack Size:

Each thread in the VM get’s a stack. 
The stack size will limit the number of threads that you can have, too big of a stack size and you will run out of memory as each thread is allocated more memory than it needs.

JVM option	Meaning
-Xss	the stack size for each thread
-Xss determines the size of the stack: –Xss1024k. 
If the stack space is too small, eventually you will see an exception class java.lang.StackOverflowError.

Garbage Collection:

There are essentially two GC threads running. 

One is a very lightweight thread which does “little” collections primarily on the Young generation of the heap. 
The other is the Full GC thread which traverses the entire heap when there is not enough memory left to allocate space for objects which get promoted from the Young to the older generation(s).

If there is a memory leak or inadequate heap allocated, 
eventually the older generation will start to run out of room causing the Full GC thread to run (nearly) continuously. 
Since this process stops the world, Java application won’t be able to respond to requests and they’ll start to back up or OOM.

The amount allocated for the Young(Eden) generation is the value specified with -Xmn. 
The amount allocated for the older generation is the value of -Xmx minus the -Xmn. 
Generally, you don’t want the Eden to be too big or it will take too long for the GC to look through it for space that can be reclaimed.


# What are the main 3 Object Oriented Programing (OOP) concepts?

1. Encapsulation

Encapsulation is a mechanism by which developer could hide implementation behind an interface.
Encapsulated code has two features:
Instance variables are kept protected (usually with the private modifier).
Getter and setter methods provide access to instance variables.
Kindly take a look at tutorial: Complete End to End working Encapsulation example
The public setName() and getName() methods are the access points of the instance variables.

2. Inheritance

Inheritance allows a class to be a subclass of a superclass, and thereby inherit public and protected variables and methods of the superclass.
Inheritance is a key concept that underlies polymorphism, overriding, overloading and casting.

3. Polymorphism

Polymorphism means “many forms.”
A reference variable is always of a single, unchangeable type, but it can refer to a subtype object.
A single object can be referred to by reference variables of many different types —as long as they are the same type or a supertype of the object.
Polymorphic method invocations apply only to overridden instance methods.


# What is immutable object? 

Immutable classes are Java classes whose objects can not be modified once created. 

Any modification in Immutable object result in new object. 

For example is String is immutable. 

Mostly Immutable are also final in Java, in order to prevent sub class from overriding.


# What is the difference between factory and abstract factory pattern?

Abstract Factory provides one more level of abstraction. 
Consider different factories each extended from an Abstract Factory and responsible for creation of different hierarchies of objects based on the type of factory. 
E.g. AbstractFactory extended by AutomobileFactory, UserFactory, RoleFactory etc. 
Each individual factory would be responsible for creation of objects in that genre.


# What is difference between Executor.submit() and Executer.execute() method ?

Executor.submit() 
accept both runable and callable argument
return Future object.
throws Exception.

future.get() will retrieve the result from callable.call() or throw exceptions

Executer.execute() 
accept both runable and has no return.


# About Singleton

Lazy Initialization
```
/* 
 * Intention is to minimize cost of synchronization and improve performance, 
 * by only locking critical section of code, the code which creates instance of Singleton class. 
 * By the way this is still broken, if we don't make _instance volatile, as another thread can 
 */

public class Singleton{
    
    private static volatile Singleton uniqueInstance;
    
    private Singleton(){}
    
    public static Singleton getInstance(){
        if (uniqueInstance ==null ){
            synchronized(Singleton.class){
                if (uniqueInstance ==null ){
                    uniqueInstance=new Singleton();
                }
            }
        }
        return uniqueInstance ;
    }
    
    // other useful methods here
}
```


# How do you ensure that N thread can access N resources without deadlock

Key point here is order, if you acquire resources in a particular order and release resources in reverse order you can prevent deadlock. 


# What is the ThreadLocal class? How and why would you use it?

A single ThreadLocal instance can store different values for each thread independently. 
Each thread that accesses the get() or set() method of a ThreadLocal instance is accessing its own, independently initialized copy of the variable. 
ThreadLocal instances are typically private static fields in classes that wish to associate state with a thread (e.g., a user ID or transaction ID). 
The example below, from the ThreadLocal Javadoc, generates unique identifiers local to each thread. A thread’s id is assigned the first time it invokes ThreadId.get() and remains unchanged on subsequent calls.
```
public class ThreadId {
    // Next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}
```
Each thread holds an implicit reference to its copy of a thread-local variable as long as the thread is alive and the ThreadLocal instance is accessible; 
after a thread goes away, all of its copies of thread-local instances are subject to garbage collection (unless other references to these copies exist).


# What is the volatile keyword? How and why would you use it?

In Java, each thread has its own stack, including its own copy of variables it can access. When the thread is created, it copies the value of all accessible variables into its own stack. The volatile keyword basically says to the JVM “Warning, this variable may be modified in another Thread”.

In all versions of Java, the volatile keyword guarantees global ordering on reads and writes to a variable. This implies that every thread accessing a volatile field will read the variable’s current value instead of (potentially) using a cached value.

In Java 5 or later, volatile reads and writes establish a happens-before relationship, much like acquiring and releasing a mutex.

Using volatile may be faster than a lock, but it will not work in some situations. The range of situations in which volatile is effective was expanded in Java 5; in particular, double-checked locking now works correctly.

The volatile keyword is also useful for 64-bit types like long and double since they are written in two operations. Without the volatile keyword you risk stale or invalid values.

One common example for using volatile is for a flag to terminate a thread. If you’ve started a thread, and you want to be able to safely interrupt it from a different thread, you can have the thread periodically check a flag (i.e., to stop it, set the flag to true). By making the flag volatile, you can ensure that the thread that is checking its value will see that it has been set to true without even having to use a synchronized block. For example:
```
public class Foo extends Thread {
    private volatile boolean close = false;
    public void run() {
        while(!close) {
            // do work
        }
    }
    public void close() {
        close = true;
        // interrupt here if needed
    }
}
```


# Compare the sleep() and wait() methods in Java, including when and why you would use one vs. the other.

sleep() is a blocking operation that keeps a hold on the monitor / lock of the shared object for the specified number of milliseconds.
sleep() is most commonly used for polling, or to check for certain results, at a regular interval. 

wait(), on the other hand, simply pauses the thread until either (a) the specified number of milliseconds have elapsed or (b) it receives a desired notification from another thread (whichever is first), without keeping a hold on the monitor/lock of the shared object.
wait() is generally used in multithreaded applications, in conjunction with notify() / notifyAll(), to achieve synchronization and avoid race conditions.


# How can you catch an exception thrown by another thread in Java?

This can be done using Thread.UncaughtExceptionHandler.

Here’s a simple example:
```
// create our uncaught exception handler
Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
    public void uncaughtException(Thread th, Throwable ex) {
        System.out.println("Uncaught exception: " + ex);
    }
};

// create another thread
Thread otherThread = new Thread() {
    public void run() {
        System.out.println("Sleeping ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
        System.out.println("Throwing exception ...");
        throw new RuntimeException();
    }
};

// set our uncaught exception handler as the one to be used when the new thread
// throws an uncaught exception
otherThread.setUncaughtExceptionHandler(handler);

// start the other thread - our uncaught exception handler will be invoked when
// the other thread throws an uncaught exception
otherThread.start();
```


# What is the Java Classloader? List and explain the purpose of the three types of class loaders.

The Java Classloader is the part of the Java runtime environment that loads classes on demand (lazy loading) into the JVM (Java Virtual Machine). 
Classes may be loaded from the local file system, a remote file system, or even the web.

When the JVM is started, three class loaders are used: 
1. Bootstrap Classloader: Loads core java API file rt.jar from folder. 
2. Extension Classloader: Loads jar files from folder. 
3. System/Application Classloader: Loads jar files from path specified in the CLASSPATH environment variable.


# When designing an abstract class, why should you avoid calling abstract methods inside its constructor?

This is a problem of initialization order. The subclass constructor will not have had a chance to run yet and there is no way to force it to run it before the parent class. Consider the following example class:
```
        public abstract class Widget {
	        private final int cachedWidth;
	        private final int cachedHeight;
	
	        public Widget() {
	            this.cachedWidth = width();
	            this.cachedHeight = height();
	        }
	
	        protected abstract int width();
	        protected abstract int height();
	    }
```
This seems like a good start for an abstract Widget: it allows subclasses to fill in width and height, and caches their initial values. However, look when you spec out a typical subclass implementation like so:
```
        public class SquareWidget extends Widget {
	        private final int size;
	
	        public SquareWidget(int size) {
	            this.size = size;
	        }
	
	        @Override
	        protected int width() {
	            return size;
	        }
	
	        @Override
	        protected int height() {
	            return size;
	        }
	    }
```
Now we’ve introduced a subtle bug: Widget.cachedWidth and Widget.cachedHeight will always be zero for SquareWidget instances! This is because the this.size = size assignment occurs after the Widget constructor runs.

Avoid calling abstract methods in your abstract classes’ constructors, as it restricts how those abstract methods can be implemented.


# What is reflection? Give an example of functionality that can only be implemented using reflection.

Reflection allows programmatic access to information about a Java program’s types. 
Commonly used information includes:
* methods and fields available on a class
* interfaces implemented by a class
* the runtime-retained annotations on classes, fields and methods

Examples given are likely to include:

* Annotation-based serialization libraries often map class fields to JSON keys or XML elements (using annotations). These libraries need reflection to inspect those fields and their annotations and also to access the values during serialization.

* Model-View-Controller frameworks call controller methods based on routing rules. These frameworks must use reflection to find a method corresponding to an action name, check that its signature conforms to what the framework expects (e.g. takes a Request object, returns a Response), and finally, invoke the method.

* Dependency injection frameworks lean heavily on reflection. They use it to instantiate arbitrary beans for injection, check fields for annotations such as @Inject to discover if they require injection of a bean, and also to set those values.

* Object-relational mappers such as Hibernate use reflection to map database columns to fields or getter/setter pairs of a class, and can go as far as to infer table and column names by reading class and getter names, respectively.

A concrete code example could be something simple, like copying an object’s fields into a map:
```
        Person person = new Person("Doug", "Sparling", 31);

        Map<String, Object> values = new HashMap<>();
        for (Field field : person.getClass().getDeclaredFields()) {
            values.put(field.getName(), field.get(person));
        }

        // prints {firstName=Doug, lastName=Sparling, age=31}
        System.out.println(values);
```
Such tricks can be useful for debugging, or for utility methods such as a toString method that works on any class.

Aside from implementing generic libraries, direct use of reflection is rare but it is still a handy tool to have. Knowledge of reflection is also useful for when these mechanisms fail.

However, it is often prudent to avoid reflection unless it is strictly necessary, as it can turn straightforward compiler errors into runtime errors.


# Are you aware of Daemon Thread in Java?

Daemon threads in Java are like a service providers for other threads or objects running in the same process as the daemon thread. 
Daemon threads are used for background supporting tasks and are only needed while normal threads are executing. 
If normal threads are not running and remaining threads are daemon threads then the interpreter exits.


# What is method overloading and method overriding?

* Method Overloading :

In Method Overloading, 
Methods of the same class shares the same name but each method must have different number of parameters or parameters having different types and order.
Method Overloading is to “add” or “extend” more to method’s behavior.
It is a compile time polymorphism.
The methods must have different signature.
It may or may not need inheritance in Method Overloading.
Let’s take a look at the example below to understand it better.
```
class Adder {
    Static int add(int a, int b) {
        return a+b;
    }
    Static double add( double a, double b) {
        return a+b;
    }
    public static void main(String args[]) {
        System.out.println(Adder.add(11,11));
        System.out.println(Adder.add(12.3,12.6));
    }
}
```
Method Overriding:  

In Method Overriding, 
sub class have the same method with same name and exactly the same number and type of parameters and same return type as a super class.
Method Overriding is to “Change” existing behavior of method.
It is a run time polymorphism.
The methods must have same signature.
It always requires inheritance in Method Overriding.
Let’s take a look at the example below to understand it better.
```
class Car {

    void run(){
        System.out.println(“car is running”); 
    }

    Class Audi extends Car{
        void run(){
            System.out.prinltn(“Audi is running safely with 100km”);
        }
    }

    public static void main( String args[]){
        Car b=new Audi();
        b.run();
    }
}
```

# Why is Java called the ‘Platform Independent Programming Language’?
 

Platform independence means that execution of your program does not dependent on type of operating system(it could be any : Linux, windows, Mac ..etc). 

So compile code only once and run it on any System (In C/C++, we need to compile the code for every machine on which we run it). 

Java is both compiler(javac) and interpreter(jvm) based lauguage. 

Your java source code is first compiled into byte code using javac compiler. 

This byte code can be easily converted to equivalent machine code using JVM. 

JVM(Java Virtual Machine) is available in all operating systems we install. 

Hence, byte code generated by javac is universal and can be converted to machine code on any operating system, this is the reason why java is platform independent.

 
# Explain Final keyword in java?
 
Final keyword in java is used to restrict usage of variable, class and method.
 
* Variable: Value of Final variable is constant, you can not change it.
* Method: you can’t override a Final method.
* Class: you can’t inherit from Final class.

 
# When is the super keyword used?
 
super keyword is used to refer:

* immediate parent class constructor,
* immediate parent class variable,
* immediate parent class method.


# What is “super” keyword in java?

The super keyword in java is a reference variable that is used to refer parent class objects. 
The keyword “super” came into the picture with the concept of Inheritance. 
Whenever you create the instance of subclass, an instance of parent class is created implicitly 
i.e. referred by super reference variable.
Various scenarios of using java super Keyword:

super is used to refer immediate parent instance variable
super is used to call parent class method
super() is used to call immediate parent constructor
Read more

# What is static variable in Java?

The static keyword in java is used for memory management mainly. We can apply java static keyword with variables, methods, blocks and nested class. The static keyword belongs to the class than instance of the class.

The static can be:

* variable (also known as class variable)
* method (also known as class method)
* block
* nested class


# Why multiple inheritance is not supported in java?
 
Java supports multiple inheritance but not through classes, it supports only through its interfaces. 
The reason for not supporting multiple inheritance is to avoid the conflict and complexity arises due to it and keep Java a Simple Object Oriented Language. 
If we recall this in C++, there is a special case of multiple inheritance (diamond problem) where you have a multiple inheritance with two classes which have methods in conflicts. 
So, Java developers decided to avoid such conflicts and didn’t allow multiple inheritance through classes at all.

 
# Can a top level class be private or protected?
 
Top level classes in java can’t be private or protected, but inner classes in java can. 
The reason for not making a top level class as private is very obvious, because nobody can see a private class and thus they can not use it. 
Declaring a class as protected also doesn’t make any sense. 
The only difference between default visibility and protected visibility is that we can use it in any package by inheriting it. 
Since in java there is no such concept of package inheritance, defining a class as protected is no different from default.

 
# What is the difference between ‘throw’ and ‘throws’ in Java Exception Handling?
 
Following are the differences between two:

* throw keyword is used to throw Exception from any method or static block whereas throws is used to indicate that which Exception can possibly be thrown by this method. If any method throws checked Exception, then caller can either handle this exception(using try catch block )or can re throw it by declaring another ‘throws’ clause in method declaration.
* throw clause can be used in any part of code where you feel a specific exception needs to be thrown to the calling method

E.g.
```
//throw
throw new Exception(“You have some exception”)
throw new IOException(“Connection failed!!”)
//throws
throws IOException, NullPointerException, ArithmeticException
```

 
# What is finalize() method?
 
Unlike c++ , we don’t need to destroy objects explicitly in Java. 
‘Garbage Collector‘ does that automatically for us. 
Garbage Collector checks if no references to an object exist, that object is assumed to be no longer required, and the memory occupied by the object can be freed. Sometimes an object can hold non-java resources such as file handle or database connection, then you want to make sure these resources are also released before object is destroyed. 
To perform such operation Java provide protected void finalize() in object class. 
You can override this method in your class and do the required tasks. 
Right before an object is freed, the java run time calls the finalize() method on that object. Refer this for more details.

# What is transient variable?、

During serialization process, transient variable states will not be serialized. State of the value will
be always defaulted after deserialization.

# Can we Overload or Override static methods in java ?

* Overriding : Overriding is related to run-time polymorphism. A subclass (or derived class) provides a specific implementation of a method in superclass (or base class) at runtime.

* Overloading: Overloading is related to compile time (or static) polymorphism. This feature allows different methods to have same name, but different signatures, especially number of input parameters and type of input paramaters.

# Can we overload static methods?   

The answer is ‘Yes’. 
We can have two ore more static methods with same name, but differences in input parameters

# Can we Override static methods in java?  
 
We cannot override static methods. Static methods are belogs to class, not belongs
to object. Inheritance will not be applicable for class members


# How garbage collector knows that the object is not in use and needs to be removed?

Garbage collector reclaims objects that are no longer being used, clears their memory, and keeps the memory available for future allocations. This is done via bookkeeping the references to the objects. Any unreferenced object is a garbage and will be collected.

# What is java classpath?

The classpath is an environment variable. It is used to
let the compiler know where the class files are available
for import.


# What is Stability in sorting algorithms

A sorting algorithm is said to be stable if two objects with equal keys appear in the same order in sorted output as they appear in the input array to be sorted.
1. buble O(n2)
2. 
