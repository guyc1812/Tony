package com.avengers.core.ZZZ.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yucgu on 2017/11/30.
 */
public class Enums {

    public static final Map<String, Boolean> FEATURE_FLAGS;

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
