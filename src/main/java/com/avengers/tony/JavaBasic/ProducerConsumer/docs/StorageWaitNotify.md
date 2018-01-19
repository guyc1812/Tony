# Producer & Consumer by wait() and notify()

### Demo

```
public class Storage_WaitNotify implements Storage {

    private final int MAX_SIZE = 100;
    private LinkedList<Object> list = new LinkedList<>();

    @Override
    public void produce(int num) {
        synchronized (list) {
            // if insufficient
            while (list.size() + num > MAX_SIZE) {
                System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for producing " + num + " items");
                try {
                    list.wait();    // wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // produce num items
            for (int i = 1; i <= num; ++i) {
                list.add(new Object());
            }
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t produced " + num);
            list.notifyAll();
        }
    }

    @Override
    public void consume(int num) {
        synchronized (list) {
            // if insufficient
            while (list.size() < num) {
                System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t insufficient for consuming " + num + " items");
                try {
                    list.wait();    // wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // consume num items
            for (int i = 1; i <= num; ++i) {
                list.remove();
            }
            System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t consumed " + num);
            list.notifyAll();
        }
    }
}
```

### Demo Test

```
public static void main(String[] args) {

    // Storage
    Storage storage = new Storage_WaitNotify();
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
Thread-1	 inventory: 80	 produced 80
Thread-7	 inventory: 50	 consumed 30
Thread-2	 inventory: 60	 produced 10
Thread-3	 inventory: 60	 insufficient for producing 50 items
Thread-0	 inventory: 90	 produced 30
Thread-4	 inventory: 100	 produced 10
Thread-5	 inventory: 100	 insufficient for producing 40 items
Thread-6	 inventory: 50	 consumed 50
Thread-9	 inventory: 40	 consumed 10
Thread-8	 inventory: 20	 consumed 20
Thread-3	 inventory: 70	 produced 50
Thread-5	 inventory: 110	 produced 40
```