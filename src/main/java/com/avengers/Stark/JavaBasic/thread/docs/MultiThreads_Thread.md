# Multiple Threads

Example question:

Input:  <br>
010203040506

Output: <br>
[thread]1:000000<br>
[thread]2:246<br>
[thread]3:135


### Extends Thread - way 1

* MultiThreads_Thread extends Thread 
* Override run() method
* Call thread.start() method
* Semaphore is used to control how many threads can be executed at a same time.

```java
public class MultiThreads_Thread extends Thread {

    private Semaphore semaphore;
    private String threadName;
    private String series;

    private MultiThreads_Thread(Semaphore semaphore, String threadName, String series) {
        this.semaphore = semaphore;
        this.threadName = threadName;
        this.series = series;
    }

    public void run() {
        try {
            semaphore.acquire();
            StringBuilder sb = new StringBuilder();
            switch (threadName) {
                case "1":
                    System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur==0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
                case "2":
                    System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur%2==0&&cur!=0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
                case "3":
                    System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
                    for(int i=0;i<this.series.length();i++){
                        int cur = Integer.parseInt(this.series.charAt(i)+"");
                        if(cur%2!=0){sb.append(cur);}
                    }
                    System.out.println("[thread]"+threadName+":"+sb.toString());
                    break;
            }
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Test

```java
public static void main(String[] args) {
    String series = "010203040506";
    Semaphore sem = new Semaphore(1);
    MultiThreads_Thread t1 = new MultiThreads_Thread(sem, "1",series);
    MultiThreads_Thread t2 = new MultiThreads_Thread(sem, "2",series);
    MultiThreads_Thread t3 = new MultiThreads_Thread(sem, "3",series);
    t1.start();
    t2.start();
    t3.start();
}
```

Output:

```bash
Thread-0 START !
Thread-2 START !
Thread-1 START !
[thread]3:135
[thread]1:000000
[thread]2:246
```

If `Semaphore sem = new Semaphore(3)`<br>
Output:

```bash
Thread-0 START !
[thread]1:000000
Thread-1 START !
[thread]2:246
Thread-2 START !
[thread]3:135
```

### use join() - way 2

```java
private static Thread t0 = new Thread(() -> {
    try {
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " =>0");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});

private static Thread t1 = new Thread(() -> {
    try {
        t0.join();
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " =>1");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});

private static Thread t2 = new Thread(() -> {
    try {
        t1.join();
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " =>2");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});

public static void main(String args[]) {
    t2.start();
    t1.start();
    t0.start();
    // Thread-0 =>0
    // Thread-1 =>1
    // Thread-2 =>2
}
```


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/thread/code)
