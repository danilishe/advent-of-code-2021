package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class D7t1 {
    public static void main(String[] args) {
        load();
        data = new ArrayList<>(data);
        Collections.sort(data);

        int avg = data.get(data.size() /2);
        long result = 0;
        for (int p : data) {
            result += Math.abs(p - avg);
        }
        System.out.println("result = " + result);
// 345035, 345037
    }

    static List<Integer> data;

    @SneakyThrows
    private static void load() {
        Scanner scanner = new Scanner(new File("C:\\Users\\sueti\\Downloads\\d7t1.txt"));
        data = Arrays.stream(scanner.nextLine().split(",")).map(Integer::valueOf).toList();
        scanner.close();
        System.out.println("data = " + data);
    }
}
