package org.example;

import javax.management.openmbean.OpenMBeanParameterInfo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class D14t2 {
    public static void main(String[] args) throws IOException {
        final List<String> d14t1 = InputDataLoader.loadLines("d14t1");
        String current = d14t1.get(0);
        final Map<String, String> keys = d14t1
                .subList(2, d14t1.size()).stream()
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        Files.writeString(Paths.get("-1.out"), current, StandardOpenOption.CREATE);
        final char[] buff = new char[2];
        for (int i = 18; i < 40; i++) {
            final Path previous = Paths.get(i - 1 + ".out");
            final long length = previous.toFile().length();
            final BufferedReader bufferedReader = Files.newBufferedReader(previous);
            final BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(i + ".out"), StandardOpenOption.CREATE);
            for (long j = 0; j < length - 2; j++) {
                bufferedReader.read(buff);
                String key = new String(buff);
                bufferedWriter.append(keys.containsKey(key) ? key.charAt(0) + keys.get(key) : key);
            }
            bufferedWriter.append(current.charAt(current.length() - 1));

            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
        }

        final BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("40.out"));
        char[] buffer = new char[1024];
        Map<Character, Long> charCount = new HashMap<>();
        while (bufferedReader.ready()) {
            for (int i = 0; i < bufferedReader.read(buffer); i++) {
                final Long val = charCount.computeIfAbsent(buffer[i], c -> 0L);
                charCount.put(buffer[i], val + 1);
            }
        }
        bufferedReader.close();

        long min = Integer.MAX_VALUE;
        long max = 0;
        for (long size: charCount.values()) {
            if (size < min) min = size;
            if (size > max) max = size;
        }
        System.out.println("max-min = " + (max - min));
    }
}
