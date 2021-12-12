package org.example;

import java.util.*;

public class D11t1 {

    final private static int[][] field = InputDataLoader.loadSingleDigitMatrix("d11t1");

    public static void main(String[] args) {
        long result = 0;
        for (int step = 0; step < 100; step++) {
            final Set<Coordinate> flashedOctopuses = new HashSet<>();

            growOctopuses();

            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[0].length; x++) {
                    LinkedList<Coordinate> octopusGoingToFlash = new LinkedList<>();
                    if (goingFlash(x, y)) {
                        octopusGoingToFlash.add(new Coordinate(x, y));
                        while (!octopusGoingToFlash.isEmpty()) {
                            final Coordinate octopus = octopusGoingToFlash.pop();
                            flashedOctopuses.add(flashOctopus(octopus));
                            octopusGoingToFlash.addAll(
                                    chargeNeighbors(octopus, flashedOctopuses)
                            );
                        }
                    }
                }
            }
            result += flashedOctopuses.size();
            System.out.println(toString(field));
            System.out.println((step + 1) + ") result = " + result);
            System.out.println();
        }
        System.out.println("-------------\n" + result);
    }

    private static void growOctopuses() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                field[y][x]++;
            }
        }
    }

    private static CharSequence toString(int[][] field) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : field) {
            for (int j : row) {
                sb.append(j == 0 ? "*" : j);
            }
            sb.append("\n");
        }
        return sb;
    }

    private static Coordinate flashOctopus(final Coordinate octopus) {
        field[octopus.y()][octopus.x()] = 0;
        return octopus;
    }

    private static List<Coordinate> chargeNeighbors(Coordinate central, Set<Coordinate> flashed) {
        List<Coordinate> result = new ArrayList<>();
        for (int y = central.y() - 1; y <= central.y() + 1; y++) {
            for (int x = central.x() - 1; x <= central.x() + 1; x++) {
                final Coordinate current = new Coordinate(x, y);
                if (!flashed.contains(current) && charge(x, y)) {
                    if (goingFlash(x, y)) {
                        result.add(current);
                        flashed.add(flashOctopus(current));
                    }
                }
            }
        }
        return result;
    }

    private static boolean goingFlash(int x, int y) {
        return field[y][x] > 9;
    }

    private static boolean charge(int x, int y) {
        if (x >= 0 && x < field[0].length
                && y >= 0 && y < field.length) {
            field[y][x]++;
            return true;
        }
        return false;
    }


    private record Coordinate(int x, int y) {
    }
}
