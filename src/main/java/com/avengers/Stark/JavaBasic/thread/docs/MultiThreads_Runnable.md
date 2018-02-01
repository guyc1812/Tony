# Multiple Threads

Example question:

Input:  <br>
010203040506

Output: <br>
[thread]1:000000<br>
[thread]2:246<br>
[thread]3:135


### implements Runnable

* MultiThreads_Runnable implements Runnable 
* Override run() method
* new Thread(Runnable).start();
* Semaphore is used to control how many threads can be executed at a same time.

```java
public class MultiThreads_Runnable implements Runnable {

    private Semaphore semaphore;
    private String threadName;
    private String series;

    public MultiThreads_Runnable(Semaphore semaphore, String threadName, String series) {
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
public static void main(String args[]) {
    String series = "010203040506";
    Semaphore sem = new Semaphore(1);
    MultiThreads_Runnable R1 = new MultiThreads_Runnable(sem, "1",series);
    MultiThreads_Runnable R2 = new MultiThreads_Runnable(sem, "2",series);
    MultiThreads_Runnable R3 = new MultiThreads_Runnable(sem, "3",series);
    Thread t1 = new Thread(R1);
    Thread t2 = new Thread(R2);
    Thread t3 = new Thread(R3);
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


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/thread/code)
