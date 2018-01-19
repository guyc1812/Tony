# Java Questions


### Can we overload static methods?

We can have two ore more static methods with same name, <br>
but differences in input parameters


### Can we Override static methods in java?

We cannot override static methods. <br>
Static methods are belongs to class, not belongs to object. <br>
Inheritance will not be applicable for class members


### What is static variable in Java?

The static keyword in java is used for memory management mainly. <br>
We can apply java static keyword with variables, methods, blocks and nested class. <br>
The static keyword belongs to the class than instance of the class.

The static can be:<br>
* variable (also known as class variable)
* method (also known as class method)
* block
* nested class


### How to ensure that N thread can access N resources without deadlock

Key point here is order, <br>
if you acquire resources in a particular order and release resources in reverse order you can prevent deadlock.


### Why multiple inheritance is not supported in java?

Java supports multiple inheritance but not through classes, it supports only through its interfaces. <br>
The reason for not supporting multiple inheritance is to avoid the conflict and complexity arises due to it <br>
and keep Java a Simple Object Oriented Language. <br>
If we recall this in C++, there is a special case of multiple inheritance (diamond problem) <br>
where you have a multiple inheritance with two classes which have methods in conflicts. <br>
So, Java developers decided to avoid such conflicts and didn’t allow multiple inheritance through classes at all.<br>


### Can a top level class be private or protected?

Top level classes in java can’t be private or protected, but inner classes in java can. <br>
The reason for not making a top level class as private is very obvious, <br>
because nobody can see a private class and thus they can not use it. <br>
Declaring a class as protected also doesn’t make any sense. <br>
The only difference between default visibility and protected visibility is that we can use it in any package by inheriting it. <br>
Since in java there is no such concept of package inheritance, defining a class as protected is no different from default.


### When designing an abstract class, why should you avoid calling abstract methods inside its constructor?

This is a problem of initialization order. <br>
The subclass constructor will not have had a chance to run yet and there is no way to force it to run it before the parent class. <br>
Consider the following example class:

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

This seems like a good start for an abstract Widget: <br>
it allows subclasses to fill in width and height, and caches their initial values. <br>
However, look when you spec out a typical subclass implementation like so:

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

Now we’ve introduced a subtle bug: <br>
Widget.cachedWidth and Widget.cachedHeight will always be zero for SquareWidget instances! <br>
This is because the this.size = size assignment occurs after the Widget constructor runs.<br>
Avoid calling abstract methods in your abstract classes’ constructors, <br>
as it restricts how those abstract methods can be implemented.


### How to design a singleton

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