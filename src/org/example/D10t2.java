package org.example;

import java.util.*;

import static org.example.InputDataLoader.loadLines;

public class D10t2 {

    final static List<String> data = loadLines("d10t1");
    private static final Map<Character, Character> brackets = Map.of(
            '(', ')',
            '[', ']',
            '{', '}',
            '<', '>');
    private static final Map<Character, Character> closingBrackets = Map.of(
            ')', '(',
            ']', '[',
            '}', '{',
            '>', '<');


    public static void main(String[] args) {
        final ArrayList<Long> results = new ArrayList<>();
        lineChecking:
        for (String line : data) {
            final LinkedList<Character> stack = new LinkedList<>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (brackets.containsKey(c)) {
                    stack.push(c);
                } else if (stack.pop() != closingBrackets.get(c)) {
                    continue lineChecking;
                }
            }

            long result = 0;
            for (Character bracket : stack) {
                result = result * 5 + getPoints(brackets.get(bracket));
            }
            results.add(result);
        }
        Collections.sort(results);
        final Long middle = results.get(results.size() / 2);
        System.out.println(results);
        System.out.println("middle = " + middle);
    }

    private static long getPoints(char missingBracket) {
        return switch (missingBracket) {
            case ')' -> 1;
            case ']' -> 2;
            case '}' -> 3;
            case '>' -> 4;
            default -> 0;
        };
    }
}
