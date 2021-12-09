package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class D9t1 {

    final static List<List<Integer>> load = load();

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        List<Integer> minimals = new ArrayList<>();
        for (int y = 0; y < load.size(); y++) {
            for (int x = 0; x < load.get(0).size(); x++) {
                final int current = getVal(y, x);
                if (current < getVal(y - 1, x)
                        && current < getVal(y + 1, x)
                        && current < getVal(y, x - 1)
                        && current < getVal(y, x + 1)) {
                    minimals.add(current);
//                    sb.append("(").append(current).append(")");
                }
//                else {
//                    sb.append(" ").append(current).append(" ");
//                }
            }
//            sb.append("\n");
        }
//        System.out.println(sb);
        System.out.println("minimals = " + minimals + " summ: " + (minimals.stream().mapToInt(Integer::intValue).sum() + minimals.size()));


    }

    private static int getVal(int y, int x) {
        if (x < 0 || y < 0 || x >= load.get(0).size() || y >= load.size()) {
            return 10;
        }
        return load.get(y).get(x);
    }


    @SneakyThrows
    private static List<List<Integer>> load() {
        final List<String> strings = Files.readAllLines(Paths.get("C:\\Users\\sueti\\Downloads\\d9t1.txt"));
        final List<List<Integer>> result = new ArrayList<>();
        for (String s : strings) {
            final List<Integer> row = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                row.add(Integer.parseInt(s.substring(i, i + 1)));
            }
            result.add(row);
        }
        return result;
    }
}
