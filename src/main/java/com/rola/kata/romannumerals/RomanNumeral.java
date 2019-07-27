package com.rola.kata.romannumerals;

import java.util.LinkedHashMap;

/**
 * Represents an immutable Roman numeral. The object-oriented immutable solution
 * to the kata.
 * 
 * @author frank
 */
public class RomanNumeral
{
    private static final LinkedHashMap<Integer, String> ARABIC_ROMAN_MAPPING = new LinkedHashMap<>(14);

    static
    {
        ARABIC_ROMAN_MAPPING.put(1000, "M");
        ARABIC_ROMAN_MAPPING.put(900, "CM");
        ARABIC_ROMAN_MAPPING.put(500, "D");
        ARABIC_ROMAN_MAPPING.put(490, "XD");
        ARABIC_ROMAN_MAPPING.put(400, "CD");
        ARABIC_ROMAN_MAPPING.put(100, "C");
        ARABIC_ROMAN_MAPPING.put(90, "XC");
        ARABIC_ROMAN_MAPPING.put(50, "L");
        ARABIC_ROMAN_MAPPING.put(40, "XL");
        ARABIC_ROMAN_MAPPING.put(10, "X");
        ARABIC_ROMAN_MAPPING.put(9, "IX");
        ARABIC_ROMAN_MAPPING.put(5, "V");
        ARABIC_ROMAN_MAPPING.put(4, "IV");
        ARABIC_ROMAN_MAPPING.put(1, "I");
    }

    private final int value;
    private final String representation;

    /**
     * Accepts an Arabic numeral as value to be converted to a Roman numeral.
     * 
     * @param arabicNumeral A value from 0 to 3999.
     */
    public RomanNumeral(final int arabicNumeral)
    {
        if (arabicNumeral < 0)
            throw new IllegalArgumentException("arabicNumeral must not by negative!");
        if (arabicNumeral > 3999)
            throw new IllegalArgumentException("arabicNumeral must not be greater than 3999!");

        this.value = arabicNumeral;

        // Eager conversion, since the whole point of this class is this conversion.
        this.representation = convertArabicNumeral(arabicNumeral);
    }

    public String asString()
    {
        return representation;
    }

    @Override
    public int hashCode()
    {
        return value;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        RomanNumeral other = (RomanNumeral) obj;

        return value == other.value;
    }

    @Override
    public String toString()
    {
        return asString() + " = " + value;
    }

    private static String convertArabicNumeral(final int value)
    {
        if (value == 0)
        {
            return "nulla";
        }

        var result = new StringBuilder();

        var n = value;

        while (n > 0)
        {
            for (var symbol : ARABIC_ROMAN_MAPPING.entrySet())
            {
                if (n >= symbol.getKey())
                {
                    result.append(symbol.getValue());
                    n -= symbol.getKey();
                    break;
                }
            }
        }

        return result.toString();
    }
}
