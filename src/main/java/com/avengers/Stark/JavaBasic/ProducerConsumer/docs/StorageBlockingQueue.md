# Producer & Consumer by BlockingQueue

not a good idea!

### Demo

```
public class Storage_BlockingQueue implements Storage {

    private final int MAX_SIZE = 100;
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(MAX_SIZE);

    public void produce(int num) {


        if (list.size() == MAX_SIZE) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: full produce failed");
            return;
        }
        // produce num items
        for (int i = 1; i <= num; ++i) {
            try {
                list.put(i);     // block automatically
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t produced " + num);
    }

    public void consume(int num) {

        if (list.size() == 0) {
            System.out.println(Thread.currentThread().getName() + "\t inventory: empty consume failed");
            return;
        }
        // consume num items
        for (int i = 1; i <= num; ++i) {
            try {
                list.take();                // block automatically
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t inventory: " + list.size() + "\t consumed " + num);
    }

}
```

### Demo Test

```
public static void main(String[] args) {

    // Storage
    Storage storage = new Storage_BlockingQueue();
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
Thread-4	 inventory: 54	 produced 10
Thread-2	 inventory: 9	 produced 10
Thread-6	 inventory: 9	 consumed 50
Thread-9	 inventory: 21	 consumed 10
Thread-5	 inventory: 55	 produced 40
Thread-3	 inventory: 50	 produced 50
Thread-7	 inventory: 20	 consumed 30
Thread-0	 inventory: 22	 produced 30
Thread-1	 inventory: 4	 produced 20
Thread-8	 inventory: 4	 consumed 20
```