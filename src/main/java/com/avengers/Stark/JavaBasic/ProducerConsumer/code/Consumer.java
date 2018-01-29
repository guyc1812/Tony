package com.avengers.Stark.JavaBasic.ProducerConsumer.code;

public class Consumer extends Thread {

    private int num;
    private Storage storage;

    public Consumer(Storage storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    public void run() {
        storage.consume(num);
    }

}
