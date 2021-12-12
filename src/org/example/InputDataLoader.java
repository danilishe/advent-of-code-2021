package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputDataLoader {

    @SneakyThrows
    static List<String> loadLines(String name) {
        return Files.readAllLines(Paths.get("input/" + name + ".txt"));
    }

    @SneakyThrows
    static int[][] loadSingleDigitMatrix(String name) {
        final List<String> strings = Files.readAllLines(Paths.get("input/" + name + ".txt"));
        final int[][] ints = new int[strings.size()][strings.get(0).length()];
        for (int y = 0; y < strings.size(); y++) {
            final String currentRow = strings.get(y);
            ints[y] = new int[currentRow.length()];
            for (int x = 0; x < currentRow.length(); x++) {
                ints[y][x] = currentRow.charAt(x) - '0';
            }
        }
        return ints;
    }
}
