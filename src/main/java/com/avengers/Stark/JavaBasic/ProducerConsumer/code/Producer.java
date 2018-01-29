package com.avengers.Stark.JavaBasic.ProducerConsumer.code;

public class Producer extends Thread {

    private int num;
    private Storage storage;

    public Producer(Storage storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    public void run() {
        storage.produce(num);
    }

}