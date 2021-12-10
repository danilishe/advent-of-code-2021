package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class D4t1 {

    static List<Integer> numberQueue;
    static List<BingoCard> cards = new ArrayList<>();

    public static void main(String[] args) {
        load();
        new D4t2().showLastWinner(numberQueue, cards);
    }

    void showWinner(List<Integer> numberQueue, List<BingoCard> cards) {
        for (int i = 0; i < numberQueue.size(); i++) {
            final var number = numberQueue.get(i);
            for (int j = 0; j < cards.size(); j++) {
                final var card = cards.get(j);
                if (card.checkWin(number)) {
                    final int sum = card.leftNumbersSum();
                    System.out.printf("at %d [%d] Here is the winner: %d, answer is: %d%n", i, number, sum, number * sum);
                    System.out.println(card);
                    return;
                }
                System.out.println(i + " {{" + j + "}}" + " " + card);
            }
        }
    }

    @SneakyThrows
    private static void load() {
        final Scanner scanner = new Scanner(new File("input/d4t1.txt"));
        numberQueue = Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("numberQueue = " + numberQueue);
        while (scanner.hasNext()) {
            final List<List<Integer>> data = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                final List<Integer> row = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    row.add(scanner.nextInt());
                }
                data.add(row);
            }
            final BingoCard card = new BingoCard(data);
            cards.add(card);
        }
        System.out.println("cards = " + cards);
        System.out.println("cards = " + cards.size());
        scanner.close();
    }

}

record BingoCard(List<List<Integer>> data) {
    public boolean checkWin(int number) {
        for (var row : data) {
            final int i = row.indexOf(number);
            if (i > -1) {
                row.set(i, null);
                return row.stream()
                        .allMatch(Objects::isNull)
                        || data.stream()
                        .map(a -> a.get(i))
                        .allMatch(Objects::isNull);
            }
        }
        return false;
    }

    public int leftNumbersSum() {
        int sum = 0;
        for (var row : data) {
            for (Integer n : row) {
                if (Objects.nonNull(n))
                    sum += n;
            }
        }
        return sum;
    }
}


class Test {
    public static void main(String... arg) {
        final List<Integer> queue = Arrays.asList(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1);
        final List<BingoCard> data = List.of(
                new BingoCard(List.of(
                        new ArrayList<>(List.of(22, 13, 17, 11, 0)),
                        new ArrayList<>(List.of(8, 2, 23, 4, 24)),
                        new ArrayList<>(List.of(21, 9, 14, 16, 7)),
                        new ArrayList<>(List.of(6, 10, 3, 18, 5)),
                        new ArrayList<>(List.of(1, 12, 20, 15, 19)))),
                new BingoCard(List.of(
                        new ArrayList<>(List.of(3, 15, 0, 2, 22)),
                        new ArrayList<>(List.of(9, 18, 13, 17, 5)),
                        new ArrayList<>(List.of(19, 8, 7, 25, 23)),
                        new ArrayList<>(List.of(20, 11, 10, 24, 4)),
                        new ArrayList<>(List.of(14, 21, 16, 12, 6)))),
                new BingoCard(List.of(
                        new ArrayList<>(List.of(14, 21, 17, 24, 4)),
                        new ArrayList<>(List.of(10, 16, 15, 9, 19)),
                        new ArrayList<>(List.of(18, 8, 23, 26, 20)),
                        new ArrayList<>(List.of(22, 11, 13, 6, 5)),
                        new ArrayList<>(List.of(2, 0, 12, 3, 7)))));

        new D4t2().showLastWinner(queue, data);
    }
}