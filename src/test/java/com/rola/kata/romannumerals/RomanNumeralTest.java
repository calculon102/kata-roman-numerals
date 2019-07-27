package com.rola.kata.romannumerals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RomanNumeralTest
{
    @ParameterizedTest
    @ValueSource(ints = { -1, Integer.MIN_VALUE })
    void doesNotAcceptNegativeValues(int negativeValue) throws Exception
    {
        assertThrows(IllegalArgumentException.class, () -> new RomanNumeral(negativeValue));
    }

    @ParameterizedTest
    @ValueSource(ints = { 4000, Integer.MAX_VALUE })
    void doesNotAcceptPositiveValuesAbove3999(int valueGreaterThan3999) throws Exception
    {
        assertThrows(IllegalArgumentException.class, () -> new RomanNumeral(valueGreaterThan3999));
    }

    @Test
    void convertsZeroToNull()
    {
        assertEquals("nulla", new RomanNumeral(0).asString());
    }

    @ParameterizedTest
    @MethodSource("provideArabicRomanNumeralMapping")
    void convertsArabicNumerals(int arabicInput, String expectedRoman) throws Exception
    {
        assertEquals(expectedRoman, new RomanNumeral(arabicInput).asString());
    }

    @ParameterizedTest
    @MethodSource("provideArabicRomanNumeralMapping")
    void hashCodeEqualsNumericValue(int arabicInput) throws Exception
    {
        assertEquals(arabicInput, new RomanNumeral(arabicInput).hashCode());
    }

    @ParameterizedTest
    @MethodSource("provideArabicRomanNumeralMapping")
    void testToString(int arabicInput, String expectedRoman) throws Exception
    {
        assertEquals(expectedRoman + " = " + arabicInput, new RomanNumeral(arabicInput).toString());
    }

    @Test
    void testEquals() throws Exception
    {
        var one = new RomanNumeral(1);

        Assertions.assertFalse(one.equals(null));
        Assertions.assertFalse(one.equals(new Object()));

        Assertions.assertTrue(one.equals(one));

        var one2 = new RomanNumeral(1);
        Assertions.assertTrue(one.equals(one2));
        Assertions.assertTrue(one2.equals(one));

        var two = new RomanNumeral(2);
        Assertions.assertFalse(one.equals(two));
        Assertions.assertFalse(two.equals(one));
    }

    @Test
    void noExceptionInValidValueRange() throws Exception
    {
        var failedValues = new HashSet<Integer>();

        for (int i = 0; i < 4000; i++)
        {
            try
            {
                new RomanNumeral(i).asString();
            } catch (Exception e)
            {
                e.printStackTrace();
                failedValues.add(Integer.valueOf(i));
            }
        }

        if (!failedValues.isEmpty())
        {
            fail("Exceptions for thrown for values " + failedValues);
        }
    }

    private static Stream<Arguments> provideArabicRomanNumeralMapping()
    {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(2, "II"),
            Arguments.of(3, "III"),
            Arguments.of(4, "IV"),
            Arguments.of(5, "V"),
            Arguments.of(6, "VI"),
            Arguments.of(7, "VII"),
            Arguments.of(8, "VIII"),
            Arguments.of(9, "IX"),
            Arguments.of(10, "X"),
            Arguments.of(11, "XI"),
            Arguments.of(12, "XII"),
            Arguments.of(13, "XIII"),
            Arguments.of(14, "XIV"),
            Arguments.of(15, "XV"),
            Arguments.of(16, "XVI"),
            Arguments.of(17, "XVII"),
            Arguments.of(18, "XVIII"),
            Arguments.of(19, "XIX"),
            Arguments.of(20, "XX"),
            Arguments.of(39, "XXXIX"),
            Arguments.of(40, "XL"),
            Arguments.of(48, "XLVIII"),
            Arguments.of(49, "XLIX"),
            Arguments.of(50, "L"),
            Arguments.of(51, "LI"),
            Arguments.of(52, "LII"),
            Arguments.of(53, "LIII"),
            Arguments.of(54, "LIV"),
            Arguments.of(55, "LV"),
            Arguments.of(56, "LVI"),
            Arguments.of(57, "LVII"),
            Arguments.of(58, "LVIII"),
            Arguments.of(59, "LIX"),
            Arguments.of(60, "LX"),
            Arguments.of(90, "XC"),
            Arguments.of(99, "XCIX"),
            Arguments.of(100, "C"),
            Arguments.of(101, "CI"),
            Arguments.of(400, "CD"),
            Arguments.of(490, "XD"),
            Arguments.of(499, "XDIX"),
            Arguments.of(500, "D"),
            Arguments.of(501, "DI"),
            Arguments.of(900, "CM"),
            Arguments.of(990, "CMXC"),
            Arguments.of(999, "CMXCIX"),
            Arguments.of(1000, "M"),
            Arguments.of(1001, "MI"),
            Arguments.of(1981, "MCMLXXXI"),
            Arguments.of(2019, "MMXIX"),
            Arguments.of(3999, "MMMCMXCIX"));
    }
}
