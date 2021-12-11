package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class D11t2 {
    final private static OctoField field = load();


    public static void main(String[] args) {
        long flashedTotal = 0;
        for (int step = 0; step < 10/*!field.allFlashed()*/; step++) {
            final Set<Coordinate> flashedAtStep = new HashSet<>();

            field.growOctopuses();

            for (int y = 0; y < field.height(); y++) {
                for (int x = 0; x < field.width(); x++) {
                    LinkedList<Coordinate> flashed = new LinkedList<>();
                    if (field.goingFlash(x, y)) {
                        flashed.add(field.flashOctopus(new Coordinate(x, y)));
                        while (!flashed.isEmpty()) {
                            final Coordinate octopus = flashed.pop();
                            flashedAtStep.add(field.flashOctopus(octopus));

                            field.growNeighbours(octopus)
                                    .stream()
                                    .filter(field::goingFlash)
                                    .forEach(o -> {
                                        field.flashOctopus(o);
                                        flashed.add(o);
                                    });
                        }
                    }
                }
            }
            flashedTotal += flashedAtStep.size();
            System.out.println(field);
            System.out.println((step + 1) + ") flashedTotal = " + flashedTotal);
            System.out.println();
        }
        System.out.println("-------------\n" + flashedTotal);
    }

    @SneakyThrows
    private static OctoField load() {
        final List<String> strings = Files.readAllLines(Paths.get("input/d11t1-test.txt"));
        final int[][] ints = new int[strings.size()][strings.get(0).length()];
        for (int y = 0; y < strings.size(); y++) {
            final String currentRow = strings.get(y);
            ints[y] = new int[currentRow.length()];
            for (int x = 0; x < currentRow.length(); x++) {
                ints[y][x] = currentRow.charAt(x) - '0';
            }
        }
        return new OctoField(ints);
    }

    private record Coordinate(int x, int y) {
    }

    private record OctoField(int[][] data) {
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int[] row : this.data) {
                for (int j : row) {
                    if (j == 0) {
                        sb.append(">*<");
                    } else sb.append(" ").append(j).append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        public int width() {
            return data[0].length;
        }

        public int height() {
            return data.length;
        }

        public boolean chargeOctopus(int x, int y) {
            if (onField(x, y)) {
                this.data[y][x]++;
                return true;
            }
            return false;
        }

        private boolean onField(int x, int y) {
            return x >= 0 && x < width()
                    && y >= 0 && y < height();
        }

        public boolean goingFlash(int x, int y) {
            return data[y][x] > 9;
        }

        public boolean goingFlash(Coordinate coordinate) {
            return goingFlash(coordinate.x(), coordinate.y());
        }

        public void growOctopuses() {
            for (int y = 0; y < height(); y++) {
                for (int x = 0; x < width(); x++) {
                    chargeOctopus(x, y);
                }
            }
        }

        public Coordinate flashOctopus(final Coordinate octopus) {
            this.data[octopus.y()][octopus.x()] = 0;
//            System.out.println(this);
            return octopus;
        }

        public boolean allFlashed() {
            for (int[] octopuses : this.data) {
                for (int o : octopuses) {
                    if (o > 0) return false;
                }
            }
            return true;
        }

        public Set<Coordinate> growNeighbours(Coordinate octopus) {
            final Set<Coordinate> result = new HashSet<>();
            for (int y = octopus.y() - 1; y <= octopus.y() + 1; y++) {
                for (int x = octopus.x() - 1; x <= octopus.x() + 1; x++) {
                    final Coordinate current = new Coordinate(x, y);
                    if (!isFlashed(octopus) && chargeOctopus(x, y)) {
                        result.add(current);
                    }
                }
            }
            return result;
        }

        private boolean isFlashed(Coordinate octopus) {
            if (onField(octopus.x, octopus.y))
                return data[octopus.y][octopus.x] == 0;
            return false;
        }
    }
}
