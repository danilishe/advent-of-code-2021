package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class D14t2 {
    public static void main(String[] args) throws IOException {
        final List<String> d14t1 = InputDataLoader.loadLines("d14t1");
        final String first = d14t1.get(0);
        Map<String, Long> initialPairsCount = new HashMap<>();
        for (int i = 0; i < first.length() - 1; i++) {
            final String key = first.substring(i, i + 2);
            final Long aLong = initialPairsCount.computeIfAbsent(key, k -> 0L);
            initialPairsCount.put(key, aLong + 1);
        }

        final Map<String, String> keys = d14t1
                .subList(2, d14t1.size()).stream()
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));


        for (int i = 0; i < 40; i++) {
            Map<String, Long> count = new HashMap<>();
            for (String s : initialPairsCount.keySet()) {
                long initialCount = initialPairsCount.get(s);
                if (keys.containsKey(s)) {
                    String k1 = s.charAt(0) + keys.get(s);
                    final long k1count = count.getOrDefault(k1, 0L);
                    count.put(k1, k1count + initialCount);

                    String k2 = keys.get(s) + s.charAt(1);
                    final long k2count = count.getOrDefault(k2, 0L);
                    count.put(k2, k2count + initialCount);
                } else {
                    final long kcount = count.getOrDefault(s, 0L);
                    count.put(s, kcount + initialCount);
                }
            }
            initialPairsCount = count;
        }

        Map<Character, Long> charCount = new HashMap<>();
        for (String s : initialPairsCount.keySet()) {
            final Long c1 = charCount.getOrDefault(s.charAt(0), 0L);
            charCount.put(s.charAt(0), initialPairsCount.get(s) + c1);
        }
        final char lastChar = first.charAt(first.length() - 1);
        charCount.put(lastChar, charCount.getOrDefault(lastChar, 0L) + 1);

        long min = Long.MAX_VALUE;
        long max = 0;
        for (long size : charCount.values()) {
            if (size < min) min = size;
            if (size > max) max = size;
        }
        System.out.println("max-min = " + (max - min));
    }
}
