package com.jossegonnza.stringcalculator;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (!numbers.contains(",")) {
            return Integer.parseInt(numbers);
        }
        String [] parts = numbers.split(",");
        int first = Integer.parseInt(parts[0]);
        int second = Integer.parseInt(parts[1]);
        return first + second;
    }
}
