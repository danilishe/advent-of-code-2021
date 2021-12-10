package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class D11t1 {

    final static List<String> data = load();



    public static void main(String[] args) {


    }


    @SneakyThrows
    private static List<String> load() {
        return Files.readAllLines(Paths.get("input/d11t1.txt"));
    }
}
