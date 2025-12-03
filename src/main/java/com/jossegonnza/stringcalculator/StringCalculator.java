package com.jossegonnza.stringcalculator;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.contains(",")) {
            String[] parts = numbers.split(",");
            int sum = 0;
            for (String part : parts) {
                sum += Integer.parseInt(part);
            }
            return sum;
        } else {
            return Integer.parseInt(numbers);
        }
    }
}
