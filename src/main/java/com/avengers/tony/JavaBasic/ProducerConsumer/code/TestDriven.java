package com.avengers.tony.JavaBasic.ProducerConsumer.code;

public class TestDriven {

    public static void main(String[] args) {

        // Storage
//        Storage storage = new Storage_WaitNotify();
//        Storage storage = new Storage_AwaitSignal();
        Storage storage = new Storage_BlockingQueue();
        // Producer
        Producer p1 = new Producer(storage, 30);
        Producer p2 = new Producer(storage, 80);
        Producer p3 = new Producer(storage, 10);
        Producer p4 = new Producer(storage, 50);
        Producer p5 = new Producer(storage, 10);
        Producer p6 = new Producer(storage, 40);
        Producer p7 = new Producer(storage, 20);
        // Consumer
        Consumer c1 = new Consumer(storage, 50);
        Consumer c2 = new Consumer(storage, 30);
        Consumer c3 = new Consumer(storage, 20);
        Consumer c4 = new Consumer(storage, 10);

        p1.start();
//        p2.start();
        p3.start();
//        p4.start();
//        p5.start();
//        p6.start();
//        p7.start();

//        c1.start();
//        c2.start();
        c3.start();
//        c4.start();
    }

}
