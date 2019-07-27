# Roman Numerals Kata

Convert numbers to Roman numerals.

## Assumptions

Use Roman symbols

	1 -> I
	5 -> V
	10 -> X
	50 -> L
	100 -> C
	500 -> D
	1000 -> M

Use the following subtractive notations

	4 -> IV
	9 -> IX
	40 -> XL
	90 -> XC
	400 -> CD
	490 -> XD
	900 -> CM

Use `nulla` for 0.

The valid input-range is 0-3999.

## Solution strategy

Single class object oriented approach in Java 11 with JUnit 5. Designing an immutable value object using TDD.