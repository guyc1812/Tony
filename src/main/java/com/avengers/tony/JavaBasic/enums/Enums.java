package com.avengers.tony.JavaBasic.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Enums {


    private static final Map<String, Boolean> FEATURE_FLAGS;

    static {
        Map<String, Boolean> flags = new HashMap<>();
        flags.put("frustrate-users", false);
        flags.put("reticulate-splines", true);
        FEATURE_FLAGS = Collections.unmodifiableMap(flags);

    }

    public static void main(String[] args) {
        System.out.println(FEATURE_FLAGS);
    }


}
