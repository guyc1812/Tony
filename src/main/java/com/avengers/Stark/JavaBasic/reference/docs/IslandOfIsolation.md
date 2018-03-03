# Island Of Isolation


### Island of Isolation:

* Object 1 references Object 2 and Object 2 references Object 1.
    Neither Object 1 nor Object 2 is referenced by any other object.
    That’s an island of isolation.

* Basically, an island of isolation is a group of objects that reference each other
    but they are not referenced by any active object in the application.
    Strictly speaking, even a single unreferenced object is an island of isolation too.

### Demo

```java
public class IslandOfIsolation {

    private IslandOfIsolation i;

    public static void main(String[] args) {

        IslandOfIsolation t1 = new IslandOfIsolation();
        IslandOfIsolation t2 = new IslandOfIsolation();

        // Object of t1 gets a copy of t2
        t1.i = t2;

        // Object of t2 gets a copy of t1
        t2.i = t1;

        // Till now no object eligible
        // for garbage collection
        t1 = null;

        // now two objects are eligible for
        // garbage collection
        t2 = null;

        // calling garbage collector
        System.gc();

    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called");
    }

}
```

Output
```bash
Finalize method called
Finalize method called
```


![here need a picture]()



[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/reference/code)



### Reference

[Island of Isolation in Java](https://www.geeksforgeeks.org/island-of-isolation-in-java)

["Island of isolation" of Garbage Collection](https://stackoverflow.com/questions/792831/island-of-isolation-of-garbage-collection)