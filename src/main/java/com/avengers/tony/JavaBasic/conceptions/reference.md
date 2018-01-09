### "Strong" Reference

"strong" -- is how they interact with the garbage collector.<br>

* Strong reference
    A Strong reference is a normal reference that protects the referred object from collection by GC.
    i.e. Never garbage collects.

* Soft reference
    A Soft reference is eligible for collection by garbage collector, 
    but will only be collected when GC'S algorithms decide that memory is low enough to warrant it.
    i.e. garbage collects before OutOfMemoryError.
    ```
    SoftReference ref = new SoftReference(new StringBuilder());
    String s = ref.get();
    ```

* Weak reference
    A Weak reference is a reference that does not protect a referenced object from collection by GC. 
    i.e. garbage collects when no Strong or Soft refs.
    ```
    WeakReference ref = new WeakReference(new StringBuilder());
    String s = ref.get();
    ```

* Phantom reference
    A Phantom reference is a reference to an object is phantomly referenced after it has been finalized, 
    but before its allocated memory has been reclaimed.
    Phantom references are safe way to know an object has been removed from memory.
    For instance, 
    consider an application that deals with large images. 
    Suppose that we want to load a big image in to memory when large image is already in memory which is ready for garbage collected.
    In such case, we want to wait until the old image is collected before loading a new one. 
    Here, the phantom reference is flexible and safely option to choose. 
    The reference of the old image will be enqueued in the ReferenceQueue once the old image object is finalized. 
    After receiving that reference, we can load the new image in to memory.
    ```
    ReferenceQueue q = new ReferenceQueue();
    PhantomReference pr = new PhantomReference(object, referenceQueue);
    // Later on another point
    Reference r = q.remove();
    // Now, clear up any thing you want
    ```


### Java is Pass by Value and Not Pass by Reference

* Pass by Value: 
    The method parameter values are **copied** to another variable and then the copied object is passed, that’s why it’s called pass by value.
* Pass by Reference: 
    An alias or reference to the actual parameter is passed to the method, that’s why it’s called pass by reference.

This swap() method test can be used with any programming language to check whether it’s pass by value or pass by reference.

```
public static void swap(Object o1, Object o2){ 
	Object temp = o1;
	o1=o2;
	o2=temp; 
}
```

Demo code

```
public class Test {

	public static void main(String[] args) {

		Balloon red = new Balloon("Red");   // memory reference 50
		Balloon blue = new Balloon("Blue"); // memory reference 100
		
		swap(red, blue);
		System.out.println("red color="+red.getColor());
		System.out.println("blue color="+blue.getColor());
		
		foo(blue);
		System.out.println("blue color="+blue.getColor());
		
	}

	private static void foo(Balloon balloon) {  // baloon=100
		balloon.setColor("Red");                // baloon=100
		balloon = new Balloon("Green");         // baloon=200
		balloon.setColor("Blue");               // baloon=200
	}

	//Generic swap method
	public static void swap(Object o1, Object o2){  // o1=50, o2=100
    	Object temp = o1;       // temp=50, o1=50,  o2=100
    	o1=o2;                  // temp=50, o1=100, o2=100
    	o2=temp;                // temp=50, o1=100, o2=50
    } 
    
}
```



### Reference

[Finalization and Phantom References](https://dzone.com/articles/finalization-and-phantom)

[Understanding Weak References](https://community.oracle.com/blogs/enicholas/2006/05/04/understanding-weak-references)

[Java is Pass by Value and Not Pass by Reference](https://www.journaldev.com/3884/java-is-pass-by-value-and-not-pass-by-reference)