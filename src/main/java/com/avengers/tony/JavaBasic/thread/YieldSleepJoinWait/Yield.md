# Yield

### Yield

* From 'running' to 'ready' state
* yield() method does not release lock
* yielded, but can still continue running again.

### From 'running' to 'ready' state

```
public class YieldDemo {
    
    private static Object obj = new Object();

    public static void main(String[] args){
        YieldThread t1 = new YieldThread("t1");
        YieldThread t2 = new YieldThread("t2");
        t1.start();
        t2.start();
    }

    static class YieldThread extends Thread{
        YieldThread(String name){
            super(name);
        }
        public void run(){
            for(int i=0; i <6; i++){
                System.out.println(this.getName()+" is running");
                if (i%2 == 0)
                    System.out.println(this.getName()+" is yielding");
                    Thread.yield();
            }
        }
    }

}
``` 

Output:
```
t2 is running
t1 is running
t1 is yielding
t2 is yielding
t1 is running
t1 is running
t1 is yielding
t1 is running
t2 is running
t1 is running
t2 is running
t1 is yielding
t2 is yielding
t2 is running
t2 is running
t2 is yielding
t2 is running
t1 is running
```


### yield() method does not release lock

```
public class YieldDemo {

    private static Object obj = new Object();

    public static void main(String[] args){
        YieldThread t1 = new YieldThread("t1");
        YieldThread t2 = new YieldThread("t2");
        t1.start();
        t2.start();
    }

    static class YieldThread extends Thread{
        YieldThread(String name){
            super(name);
        }
        public void run(){
            synchronized (obj) {
                for(int i=0; i <6; i++){
                    System.out.println(this.getName()+" is running");
                    if (i%2 == 0)
                        System.out.println(this.getName()+" is yielding");
                        Thread.yield();
                }
            }
        }
    }

}
```

Output:
```
t1 is running
t1 is yielding
t1 is running
t1 is running
t1 is yielding
t1 is running
t1 is running
t1 is yielding
t1 is running
t2 is running
t2 is yielding
t2 is running
t2 is running
t2 is yielding
t2 is running
t2 is running
t2 is yielding
t2 is running
```