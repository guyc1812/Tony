package com.avengers.Stark.JavaBasic.ProducerConsumer.code;

public interface Storage {
    void produce(int num);

    void consume(int num);
}
