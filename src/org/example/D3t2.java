package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class D3t2 {

    private static char getMaxChar(List<String> data, int index, boolean returnOnesOnEquality) {
        final double half = (double) data.size() / 2;
        int onesCount = 0;
        for (String s : data) {
            if (s.charAt(index) == '1') {
                onesCount++;
            }
        }
        char result;
        if (onesCount < half) {
            result = returnOnesOnEquality ? '0' : '1';
        } else {
            result = returnOnesOnEquality ? '1' : '0';
        }
        System.out.printf("%d) Half of %d is %f, onesCount %d, using %c%n", index, data.size(), half, onesCount, result);
        return result;
    }

    public static void main(String[] args) {
        final List<String> data = array();

        String o2 = getCandidate(data, true);
        final int o2val = Integer.parseInt(o2, 2);
        System.out.println("o2val = " + o2val);

        String co2 = getCandidate(data, false);
        final int co2val = Integer.parseInt(co2, 2);
        System.out.println("co2val = " + co2val);

        System.out.println("result = " + co2val * o2val);
    }

    private static String getCandidate(final List<String> data, boolean returnMax) {
        List<String> candidates = new ArrayList<>(data);
        for (int i = 0; candidates.size() > 1; i++) {
            char filterChar = getMaxChar(candidates, i, returnMax);

            List<String> filtered = new ArrayList<>();
            for (String c : candidates) {
                if (c.charAt(i) == filterChar) filtered.add(c);
            }
            candidates = filtered;
        }

        System.out.println("candidates = " + candidates);
        return candidates.get(0);
    }

    private static List<String> array() {
        try {
            return Files.readAllLines(Paths.get("input/d3t2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}