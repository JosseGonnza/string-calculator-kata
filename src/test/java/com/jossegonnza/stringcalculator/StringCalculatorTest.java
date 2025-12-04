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

    @Test
    void shouldReturnSumForManyCommaSeparatedNumbers() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("1,2,3,4,5,6,7,8,9");

        assertEquals(45, result);
    }

    @Test
    void shouldSupportNewLineAsSeparatorTogetherWithComma() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("1\n2,3");

        assertEquals(6, result);
    }

}