package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.example.InputDataLoader.loadLines;

public class D10t1 {

    final static List<String> data = loadLines("d10t1");
    private static final Map<Character, Character> brackets = Map.of(')', '(',
            ']', '[',
            '}', '{',
            '>', '<');


    public static void main(String[] args) {
        long result = 0;
        lineChecking:
        for (String line : data) {
            final LinkedList<Character> stack = new LinkedList<>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (brackets.containsValue(c)) {
                    stack.push(c);
                } else {
                    if (stack.pop() != brackets.get(c)) {
                        result += getErrorCost(c);
                        continue lineChecking;
                    }
                }
            }
        }
        System.out.println("result = " + result);

    }

    private static int getErrorCost(char anError) {
        return switch (anError) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
    }
}
