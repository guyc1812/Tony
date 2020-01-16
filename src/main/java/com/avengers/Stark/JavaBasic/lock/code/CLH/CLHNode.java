package com.avengers.Stark.JavaBasic.lock.code.CLH;

import lombok.Data;

@Data
class CLHNode {

    private String name;

    private volatile boolean locked = true;

    CLHNode(String name) {
        this.name = name;
    }

}