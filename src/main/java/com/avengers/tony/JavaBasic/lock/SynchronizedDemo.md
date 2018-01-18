# Synchronized

### Demo

```
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

```
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

### Output: 