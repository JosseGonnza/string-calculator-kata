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

            List<String> separators = new ArrayList<>();

            if (separator.startsWith("[") && separator.endsWith("]")) {
                int index = 0;
                while (index < separator.length()) {
                    int start = separator.indexOf('[', index);
                    if (start == -1) break;

                    int end = separator.indexOf(']', start);
                    String sep = separator.substring(start + 1, end);
                    separators.add(sep);

                    index = end + 1;
                }
            } else {
                separators.add(separator);
            }

            String regex = separators.stream()
                    .map(Pattern :: quote)
                    .collect(Collectors.joining("|"));

            String[] parts = rest.split(regex);
            return getSum(parts);
        }

        String normalized = numbers.replace("\n", ",");
        if (!normalized.contains(",")) {
            int value = Integer.parseInt(normalized);
            if (value < 0) {
                throw new IllegalArgumentException("negatives not allowed: " + value);
            }
            if (value > 1000) return 0;
            return value;
        } else {
            String[] parts = normalized.split(",");
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
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("negatives not allowed: " + message);
        }
        return sum;
    }
}
