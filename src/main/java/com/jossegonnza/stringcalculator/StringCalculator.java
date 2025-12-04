package com.jossegonnza.stringcalculator;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String nomalized = numbers.replace("\n", ",");
        if (nomalized.contains(",")) {
            String[] parts = nomalized.split(",");
            int sum = 0;
            for (String part : parts) {
                sum += Integer.parseInt(part);
            }
            return sum;
        } else {
            return Integer.parseInt(nomalized);
        }
    }
}
