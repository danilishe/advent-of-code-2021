package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class D10t1 {

    final static List<String> data = load();
    private static final Map<Character, Character> brackets = Map.of(')','(',
            ']','[',
            '}','{',
            '>','<');


    public static void main(String[] args) {
        load();
        long result = 0;
        lineChecking: for (String line : data) {
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


    @SneakyThrows
    private static List<String> load() {
        return Files.readAllLines(Paths.get("C:\\Users\\sueti\\Downloads\\d10t1.txt"));
    }
}
