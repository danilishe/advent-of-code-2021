package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class D14t1 {
    public static void main(String[] args) {
        final List<String> d14t1 = InputDataLoader.loadLines("d14t1");
        String current = d14t1.get(0);
        final Map<String, String> keys = d14t1
                .subList(2, d14t1.size()).stream()
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println("current = " + current);

        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < current.length() - 1; j++) {
                final String key = current.substring(j, j + 2);
                sb.append(keys.containsKey(key) ? key.charAt(0) + keys.get(key) : key);
            }
            sb.append(current.charAt(current.length() - 1));
            System.out.println(i + ") sb = " + sb);
            current = sb.toString();
        }
        long min = Integer.MAX_VALUE;
        long max = 0;
        final Map<String, List<String>> collect = Arrays.stream(current.split("")).collect(Collectors.groupingBy(String::valueOf));
        for (Map.Entry<String, List<String>> stringListEntry : collect.entrySet()) {
            final int size = stringListEntry.getValue().size();
            if (size < min) min = size;
            if (size > max) max = size;
        }
        System.out.println("collect = " + collect);
        System.out.println("max = " + max);
        System.out.println("min = " + min);
        System.out.println("max-min = " + (max - min));
    }
}
