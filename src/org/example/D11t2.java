package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class D11t2 {

    final static List<String> data = load();



    public static void main(String[] args) {


    }


    @SneakyThrows
    private static List<String> load() {
        return Files.readAllLines(Paths.get("input/d11t2.txt"));
    }
}
