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

    @Test
    void shouldReturnNumberValueWhenSingleNumberProvided() {
        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add("4");

        assertEquals(4, result);
    }

    @Test
    void shouldReturnSumWhenTwoNumbersProvidedSeparatedWithComma() {
        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add("1,2");

        assertEquals(3, result);
    }

    @Test
    void shouldReturnSumWhenThreeNumbersProvided() {
        StringCalculator stringCalculator = new StringCalculator();

        int result = stringCalculator.add("1,2,3");

        assertEquals(6, result);
    }

}