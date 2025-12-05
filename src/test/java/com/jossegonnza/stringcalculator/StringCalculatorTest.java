package com.jossegonnza.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void shouldSupportOnlyNewLineAsSeparator() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("1\n2\n3");

        assertEquals(6, result);
    }

    @Test
    void shouldSupportCustomSingleCharacterSeparator() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("//;\n1;2");

        assertEquals(3, result);
    }

    @Test
    void shouldSupportDifferentCustomSeparator() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("//@\n1@2@3");

        assertEquals(6, result);
    }

    @Test
    void shouldThrowExceptionWhenSingleNegativeNumberIsProvided() {
        StringCalculator calculator = new StringCalculator();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("-1")
        );

        assertEquals("negatives not allowed: -1", exception.getMessage());
    }

    @Test
    void shouldListAllNegativeNumbersInExceptionMessage() {
        StringCalculator calculator = new StringCalculator();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,-2,-3")
        );

        assertEquals("negatives not allowed: -2, -3", exception.getMessage());
    }

    @Test
    void shouldIgnoreNumbersGreaterThanOneThousand() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("2,1001");

        assertEquals(2, result);
    }

    @Test
    void shouldIncludeNumberOneThousandInSum() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("1000,2");

        assertEquals(1002, result);
    }

    @Test
    void shouldReturnZeroWhenThereAreOnlyOneThousand() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("1001");

        assertEquals(0, result);
    }

    @Test
    void shouldSupportCustomSeparatorWithArbitraryLengthBetweenBrackets() {
        StringCalculator calculator = new StringCalculator();

        int result1 = calculator.add("//[***]\n1***2***3");
        int result2 = calculator.add("//[**]\n1**2**3");
        int result3 = calculator.add("//[----]\n1----2----3");
        int result4 = calculator.add("//[ñ]\n1ñ2ñ3");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//[***]\n1***-2***-3")
        );

        assertEquals(6, result1);
        assertEquals(6, result2);
        assertEquals(6, result3);
        assertEquals(6, result4);

        assertEquals("negatives not allowed: -2, -3", exception.getMessage());
    }

    @Test
    void shouldSupportMultipleSingleCharacterCustomSeparator() {
        StringCalculator calculator = new StringCalculator();

        int result = calculator.add("//[*][%]\n1*2%3");

        assertEquals(6, result);
    }

    @Test
    void shouldSupportMultipleCustomSeparatorWithLongerLength() {
        StringCalculator calculator = new StringCalculator();

        int result1 = calculator.add("//[foo][bar]\n1foo2bar3");
        int result2 = calculator.add("//[****][bar][--]\n1****2bar3--4");

        assertEquals(6, result1);
        assertEquals(10, result2);
    }
}