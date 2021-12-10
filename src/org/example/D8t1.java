package org.example;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class D8t1 {
    static List<List<String>> patterns = new ArrayList<>();
    static List<List<String>> output = new ArrayList<>();

    public static void main(String[] args) {
        long result = 0;

        load();
        for (int i = 0; i < patterns.size(); i++) {
            final List<String> cPatterns = patterns.get(i);
            final String[] p = new String[10];
            p[8] = withLength(cPatterns, 7).get(0);
            p[7] = withLength(cPatterns, 3).get(0);
            p[4] = withLength(cPatterns, 4).get(0);
            p[1] = withLength(cPatterns, 2).get(0);

            final List<String> p069 = withLength(cPatterns, 6);
            p[6] = withoutSomeSignals(p069, p[1]); /// !!!!
            p069.remove(p[6]);

            final List<String> p235 = withLength(cPatterns, 5);
            p[3] = patternContainsSignals(p235, p[1]);
            p235.remove(p[3]);

            p[5] = signalsIncludePattern(p[6], p235);
            p235.remove(p[5]);

            p[2] = p235.get(0);
            p[9] = patternContainsSignals(p069, p[5]);
            p069.remove(p[9]);
            p[0] = p069.get(0);

            final String decoded = decode(output.get(i), p);
            System.out.println(i + ") " + patterns.get(i) + " : " + output.get(i) + " - " + decoded);
            result += Long.parseLong(decoded);
        }
        System.out.println("result = " + result);

    }

    private static String decode(List<String> output, String[] patternsTable) {
        List<String> table = List.of(patternsTable);
        final StringBuilder sb = new StringBuilder();
        for (String digit : output) {
            sb.append(table.indexOf(digit));
        }
        return sb.toString();
    }

    private static String withoutSomeSignals(List<String> cPatterns, String notFullyAcceptableSignals) {
        for (String cPattern : cPatterns) {
            if (!patternContainsAllSignals(cPattern, notFullyAcceptableSignals)) {
                return cPattern;
            }
        }
        throw new RuntimeException(cPatterns + " all patterns include all symbols " + notFullyAcceptableSignals);
    }

    private static String patternContainsSignals(List<String> cPatterns, String acceptableSignals) {
        for (String cPattern : cPatterns) {
            if (patternContainsAllSignals(cPattern, acceptableSignals)) {
                return cPattern;
            }
        }
        throw new RuntimeException(cPatterns + " not contains signals " + acceptableSignals);
    }

    private static String signalsIncludePattern(String acceptableSignals, List<String> cPatterns) {
        for (String cPattern : cPatterns) {
            if (patternContainsAllSignals(acceptableSignals, cPattern)) {
                return cPattern;
            }
        }
        throw new RuntimeException(cPatterns + " not contains signals " + acceptableSignals);
    }

    private static boolean patternContainsAllSignals(String cPattern, String acceptableSignals) {
        for (char c : acceptableSignals.toCharArray()) {
            if (!cPattern.contains(String.valueOf(c))) return false;
        }
        return true;
    }

    private static List<String> withLength(List<String> cPatterns, int signals) {
        List<String> result = new ArrayList<>();
        for (String cPattern : cPatterns) {
            if (cPattern.length() == signals)
                result.add(cPattern);
        }
        if (result.isEmpty()) throw new RuntimeException("no pattern with " + signals + " signals");
        return result;
    }

    @SneakyThrows
    private static void load() {
        Scanner scanner = new Scanner(new File("input/d8t1.txt"));
        while (scanner.hasNext()) {
            final String[] split = scanner.nextLine().split("\\|");
            List<String> patternsAt = sortedPatterns(split[0].trim().split(" "));
            List<String> digs = sortedPatterns(split[1].trim().split(" "));
            output.add(digs);
            patterns.add(patternsAt);
        }
        scanner.close();
    }

    private static List<String> sortedPatterns(String[] split) {
        return Arrays.stream(split)
                .map(s -> {
                    final char[] chars = s.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                })
                .toList();
    }
}
