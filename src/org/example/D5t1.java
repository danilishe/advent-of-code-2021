package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class D5t1 {


    public static void main(String[] args) {
        final int[][] ints = new int[1000][1000];
        final List<Line> drawnLines = load().stream()
                .map(Line::new)
//                .filter(l -> l.isHorizontal() || l.isVertical())
                .peek(l -> l.draw(ints)).toList();
        int overlaps = countOverlaps(ints);
        showCanvas(ints);
        System.out.println("overlaps = " + overlaps);
    }

    private static int countOverlaps(int[][] ints) {
        int count = 0;
        for (int[] a: ints) {
            for (int i : a) {
                if (i > 1) count++;
            }
        }
        return count;
    }

    @SneakyThrows
    private static List<String> load() {
        return Files.readAllLines(Paths.get("C:\\Users\\sueti\\Downloads\\d5t2.txt"));
    }

    public static void showCanvas(int[][] canvas) {
        System.out.println(String.join("\n", Arrays.stream(canvas).map(Arrays::toString).toList()));
    }

    static class Line {
        int x1, x2, y1, y2;

        public Line(String exp) {
            final String[] split = exp.split("\\D+");
            x1 = Integer.parseInt(split[0]);
            y1 = Integer.parseInt(split[1]);
            x2 = Integer.parseInt(split[2]);
            y2 = Integer.parseInt(split[3]);
        }

        public boolean isHorizontal() {
            return y1 == y2;
        }

        public boolean isVertical() {
            return x1 == x2;
        }

        public void draw(int[][] canvas) {
            assert canvas.length > Math.max(y1, y2);
            assert canvas[0].length > Math.max(x1, x2);
            final int xLength = x2 - x1;
            final int yLength = y2 - y1;
//            final double length = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
            final int pixelLength = Math.max(Math.abs(xLength), Math.abs(yLength));
            for (double i = 0; i <= pixelLength; i++) {
                double p = i / pixelLength;
                int x = (int) Math.round(x1 + xLength * p);
                int y = (int) Math.round(y1 + yLength * p);
                canvas[y][x] += 1;
            }
        }
    }
}