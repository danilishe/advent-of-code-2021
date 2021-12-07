package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class D6t1 {
    private static final long[] fishes = new long[9];

    public static void main(String[] args) {
       load();
        for (int i = 0; i < 256; i++) {
            long newFishes = fishes[0];
            System.arraycopy(fishes, 1, fishes, 0, fishes.length - 1);
            fishes[6] += newFishes;
            fishes[8] = newFishes;
        }
        System.out.println(Arrays.toString(fishes));
        System.out.println(Arrays.stream(fishes).sum());
    }

    @SneakyThrows
    private static void load() {
        Scanner scanner = new Scanner(new File("C:\\Users\\sueti\\Downloads\\d6t2.txt"));
        for (int i : Arrays.stream(scanner.nextLine().split(",")).map(Integer::valueOf).toList()) {
            fishes[i]++;
        }
        scanner.close();
        System.out.println(Arrays.toString(fishes));
    }
}
