package com.avengers.Stark.JavaBasic.lock.code.MCS;

import lombok.Data;

@Data
public class MCSNode {

    private String name;

    boolean locked = false;
    MCSNode next = null;

    MCSNode(String name) {
        this.name = name;
    }

}
