# Producer & Consumer by await() and signal()

### Demo

```
public class Storage_AwaitSignal implements Storage {

    private final int MAX_SIZE = 100;
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private LinkedList<Object> list = new LinkedList<>();

    public void produce(int num) {

        lock.lock();
        // if insufficient
        if (list.size() + num > MAX_SIZE) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for producing " + num + " items");
            try {
                full.await();   // await
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // produce num items
        for (int i = 1; i <= num; ++i) {
            list.add(new Object());
        }
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t produced " + num);

        // wake others
        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }

    public void consume(int num) {

        lock.lock();
        // if insufficient
        while (list.size() < num) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for consuming " + num + " items");
            try {
                empty.await();  // await
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // consume num items
        for (int i = 1; i <= num; ++i) {
            list.remove();
        }
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t consumed " + num);

        // wake others
        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }
    
}
```

### Demo Test

```
public static void main(String[] args) {

    // Storage
    Storage storage = new Storage_AwaitSignal();
    // Producer
    Producer p1 = new Producer(storage, 30);
    Producer p2 = new Producer(storage, 80);
    Producer p3 = new Producer(storage, 10);
    Producer p4 = new Producer(storage, 50);
    Producer p5 = new Producer(storage, 10);
    Producer p6 = new Producer(storage, 40);
    // Consumer
    Consumer c1 = new Consumer(storage, 50);
    Consumer c2 = new Consumer(storage, 30);
    Consumer c3 = new Consumer(storage, 20);
    Consumer c4 = new Consumer(storage, 10);

    p1.start();
    p2.start();
    p3.start();
    p4.start();
    p5.start();
    p6.start();

    c1.start();
    c2.start();
    c3.start();
    c4.start();
    
}
```

### Output:

```
Thread-0	 inventory: 30	 produced 30
Thread-1	 inventory: 30	 insufficient for producing 80 items
Thread-2	 inventory: 40	 produced 10
Thread-3	 inventory: 90	 produced 50
Thread-4	 inventory: 100	 produced 10
Thread-5	 inventory: 100	 insufficient for producing 40 items
Thread-6	 inventory: 50	 consumed 50
Thread-7	 inventory: 20	 consumed 30
Thread-9	 inventory: 10	 consumed 10
Thread-8	 inventory: 10	 insufficient for consuming 20 items
Thread-1	 inventory: 90	 produced 80
Thread-5	 inventory: 130	 produced 40
Thread-8	 inventory: 110	 consumed 20
```