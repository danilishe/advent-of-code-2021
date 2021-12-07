package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;

public class D8t1 {
    public static void main(String[] args) {

    }

    @SneakyThrows
    private static void load() {
        Scanner scanner = new Scanner(new File("C:\\Users\\sueti\\Downloads\\d6t2.txt"));

        scanner.close();
    }
}
