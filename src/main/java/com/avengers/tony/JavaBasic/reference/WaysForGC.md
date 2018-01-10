# Ways for Garbage Collection

### four different ways to make an object eligible for GC.

* Nullifying the reference variable
* Re-assigning the reference variable
* Object created inside method
* Island of Isolation


### Nullifying the reference variable

```
WaysForGC_1 t1 = new WaysForGC_1("t1");
t1 = null;

System.gc();
```

[CODE]()


### Re-assigning the reference variable

```
WaysForGC_2 t1 = new WaysForGC_2("t1");
WaysForGC_2 t2 = new WaysForGC_2("t2");
t1 = t2;

System.gc();

// t1 be collected
```

[CODE]()


### Object created inside method

```
static void show() {
    //object t1 inside method becomes unreachable when show() removed
    WaysForGC_3 t1 = new WaysForGC_3("t1");
    display();
}

static void display() {
    //object t2 inside method becomes unreachable when display() removed
    WaysForGC_3 t2 = new WaysForGC_3("t2");
}

public static void main(String args[]) {
    show();
    System.gc();
}

// t2 successfully garbage collected
// t1 successfully garbage collected
```

[CODE]()


### Island of Isolation

```
new WaysForGC_4("t1");  // no reference
System.gc();
```

[CODE]()

