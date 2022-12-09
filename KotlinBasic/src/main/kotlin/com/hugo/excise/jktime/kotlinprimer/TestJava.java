package com.hugo.excise.jktime.kotlinprimer;

import kotlin.io.FilesKt;
import kotlin.text.Charsets;

import java.io.File;

public class TestJava {
    public static void main(String[] args) {
//        TestStatic.INSTANCE.sayMessage("hello");
        TestStatic.sayMessage("hello");

        File file = new File("src/main/kotlin/com/hugo/excise/jktime/kotlinprimer/test.txt");
        String content = FilesKt.readText(file, Charsets.UTF_8);
        System.out.println(content);
    }
}
