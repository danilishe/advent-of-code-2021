package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class D7t2 {
    public static void main(String[] args) {
        final ArrayList<Long> summs = new ArrayList<>(2000);
        summs.add(0, 0l);
        for (int i = 1; i < 2000; i++) {
            summs.add(i, summs.get(i - 1) + i);
        }
        System.out.println("summs = " + summs);
        load();

        List<Long> results = new ArrayList<>();
        for (int i = 0; i < data.stream().max(Integer::compareTo).get(); i++) {
            long result = 0;
            for (int p : data) {
                int change = Math.abs(p - i);
                result += summs.get(change);
            }
            results.add(result);
        }
        final ArrayList<Long> unmodified = new ArrayList<>(results);

        Collections.sort(results);
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d) : %d %n", unmodified.indexOf(results.get(i)), results.get(i));
        }

    }

    static List<Integer> data;

    @SneakyThrows
    private static void load() {
        Scanner scanner = new Scanner(new File("input/d7t1.txt"));
        data = Arrays.stream(scanner.nextLine().split(",")).map(Integer::valueOf).toList();
        scanner.close();
        System.out.println("data = " + data);
    }
}
