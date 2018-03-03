# Synchronized

### Demo

```java
public class SynchronizedDemo {

    // no Synchronized
    public void syncNone() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized on Static
    public static synchronized void syncStatic() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized on Method
    public synchronized void syncMethod1() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized on Method
    public synchronized void syncMethod2() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t count is " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized on Block
    public void syncBlock() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + "\t count is " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
```

### Demo Test

```java
public static void main(String[] args) {

    SynchronizedDemo sd = new SynchronizedDemo();

    Thread t0 = new Thread(sd::syncMethod1);
    Thread t1 = new Thread(sd::syncMethod2);
    Thread t2 = new Thread(sd::syncBlock);
    Thread t3 = new Thread(sd::syncNone);
    Thread t4 = new Thread(SynchronizedDemo::syncStatic);
    Thread t5 = new Thread(SynchronizedDemo::syncStatic);

    t0.start();
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    
}
```

### Output: Synchronized Method & Synchronized Method

```bash
Thread-1	 count is 0     # Synchronized Method
Thread-1	 count is 1
Thread-1	 count is 2
Thread-1	 count is 3
Thread-1	 count is 4
Thread-0	 count is 0     # Synchronized Method
Thread-0	 count is 1
Thread-0	 count is 2
Thread-0	 count is 3
Thread-0	 count is 4
```

### Output: Synchronized Method & Synchronized Block

```bash
Thread-0	 count is 0     # Synchronized Method
Thread-0	 count is 1
Thread-0	 count is 2
Thread-0	 count is 3
Thread-0	 count is 4
Thread-2	 count is 0     # Synchronized Block
Thread-2	 count is 1
Thread-2	 count is 2
Thread-2	 count is 3
Thread-2	 count is 4
```

### Output: Synchronized Method & Synchronized Static

```bash
Thread-4	 count is 0     # Synchronized Static
Thread-0	 count is 0     # Synchronized Method
Thread-0	 count is 1
Thread-4	 count is 1
Thread-0	 count is 2
Thread-4	 count is 2
Thread-0	 count is 3
Thread-4	 count is 3
Thread-0	 count is 4
Thread-4	 count is 4
```

### Output: Synchronized Block & Synchronized Static

```bash
Thread-2	 count is 0     # Synchronized Block
Thread-4	 count is 0     # Synchronized Static
Thread-4	 count is 1
Thread-2	 count is 1
Thread-4	 count is 2
Thread-2	 count is 2
Thread-4	 count is 3
Thread-2	 count is 3
Thread-4	 count is 4
Thread-2	 count is 4
```

### Output: Synchronized Static & Synchronized Static

```bash
Thread-5	 count is 0     # Synchronized Static
Thread-5	 count is 1
Thread-5	 count is 2
Thread-5	 count is 3
Thread-5	 count is 4
Thread-4	 count is 0     # Synchronized Static
Thread-4	 count is 1
Thread-4	 count is 2
Thread-4	 count is 3
Thread-4	 count is 4
```
