package com.jossegonnza.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        if (numbers.startsWith("//")) {
            int newLine = numbers.indexOf("\n");
            String separator = numbers.substring(2, newLine);
            String rest = numbers.substring(newLine + 1);

            String[] parts = rest.split(Pattern.quote(separator));
            return getSum(parts);
        }

        String nomalized = numbers.replace("\n", ",");
        if (!nomalized.contains(",")) {
            int value = Integer.parseInt(nomalized);
            if (value < 0) {
                throw new IllegalArgumentException("negatives not allowed: " + value);
            }
            return value;
        } else {
            String[] parts = nomalized.split(",");
            return getSum(parts);
        }
    }

    private static int getSum(String[] parts) {
        int sum = 0;
        List<String> negatives = new ArrayList<>();
        for (String part : parts) {
            int value = Integer.parseInt(part);
            if (value > 1000) continue;
            if (value < 0) {
                negatives.add(part);
            }
            sum += value;
        }
        if (!negatives.isEmpty()) {
            String message = negatives.stream()
                    .map(String :: valueOf)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("negatives not allowed: " + message);
        }
        return sum;
    }
}
