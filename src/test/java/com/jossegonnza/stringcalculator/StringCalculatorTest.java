package com.jossegonnza.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {
    @Test
    void shouldReturnZeroWhenInputIsEmptyString() {
        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add("");

        assertEquals(0, result);
    }
}