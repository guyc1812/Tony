package com.avengers.tony.JavaBasic.readFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {


    public static String read(String filePath) {

        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String readByLines(String filePath) {

        StringBuilder result = new StringBuilder();
        try {
            Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8).forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }

}
