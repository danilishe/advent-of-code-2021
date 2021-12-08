package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class D9t1 {

    public static void main(String[] args) {
        load();

    }


    @SneakyThrows
    private static void load() {
        Scanner scanner = new Scanner(new File("C:\\Users\\sueti\\Downloads\\d8t1.txt"));

        scanner.close();
    }
}
