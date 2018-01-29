package com.avengers.tony.JavaBasic.io;

import com.avengers.Stark.JavaBasic.io.code.ReadFile;
import org.junit.Test;

public class ReadFileTest {

    @Test
    public void read() {
        String fileString = ReadFile.read("Tony.txt");
        System.out.println(fileString);     // fileString: "Tony\r\Hulk\r\n"
    }

    @Test
    public void readByLines() {
        String fileString = ReadFile.readByLines("Tony.txt");
        System.out.println(fileString);     // fileString: "TonyHulk"
    }

}
