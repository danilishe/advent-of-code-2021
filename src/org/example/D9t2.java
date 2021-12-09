package org.example;

import lombok.SneakyThrows;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class D9t2 {

    final static List<List<Integer>> data = load();
    final static List<Integer> bassins = new ArrayList<>();

    public static void main(String[] args) {
        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data.get(0).size(); x++) {
                if (getValOnce(y, x) < 9) countBassin(y, x);
            }
        }
        bassins.sort(Comparator.reverseOrder());
//        System.out.println("final ~~~~~ ");
//        print();
//        System.out.println("bassins = " + bassins);
        System.out.println("bassins = " + (bassins.get(0) * bassins.get(1) * bassins.get(2)));
    }

    private static void countBassin(int y0, int x0) {
        final LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(x0, y0));
        int currentBassin = 0;
        while (!points.isEmpty()) {
            final Point first = points.pop();
            final int x = (int) first.getX();
            final int y = (int) first.getY();
            if (getValOnce(y + 1, x) < 9) points.add(new Point(x, y + 1));
            if (getValOnce(y, x - 1) < 9) points.add(new Point(x - 1, y));
            if (getValOnce(y, x + 1) < 9) points.add(new Point(x + 1, y));
            if (getValOnce(y - 1, x) < 9) points.add(new Point(x, y - 1));
            currentBassin++;
        }
        bassins.add(currentBassin);
        System.out.println("currentBassin = " + currentBassin);
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        data.forEach(row -> {
            row.forEach(i -> sb.append(i == null ? " " : i));
            sb.append("\n");
        });
        System.out.println(sb);
    }

    private static int getValOnce(int y, int x) {
        if (x < 0 || y < 0 || x >= data.get(0).size() || y >= data.size()) {
            return 10;
        }
        final Integer integer = data.get(y).get(x);
        if (integer != null && integer < 9) data.get(y).set(x, null);
        return integer != null ? integer : 10;
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
