package com.elypia.elypiai.utils.math;

public enum Number {

	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	ELEVEN(11),
	TWELVE(12),
	THIRTEEN(13),
	FOURTEEN(14),
	FIFTEEN(15),
	SIXTEEN(16),
	SEVENTEEN(17),
	EIGHTEEN(18),
	NINETEEN(19),
	TWENTY(20),
	THIRTY(30),
	FOURTY(40),
	FIFTY(50),
	SIXTY(60),
	SEVENTY(70),
	EIGHTY(80),
	NINETY(90),
	HUNDRED(100),
	THOUSAND(1000),
	MILLION(1000000),
	BILLION(1000000000),
	TRILLION(1000000000000L),
	QUADRILLION(1000000000000000L),
	QUINTILLION(1000000000000000000L);

	private long value;

	Number(long number) {
		this.value = number;
	}

	public long asNumeric() {
		return value;
	}

	public String getName() {
		return toString().toLowerCase();
	}

	public static Number[] of(char... chars) {
		int length = chars.length;
		Number[] numbers = new Number[chars.length];

		for (int i = 0; i < length; i++)
			numbers[i] = of(chars[i] - 48);

		return numbers;
	}

	public static Number of(long i) {
		for (Number num : values()) {
			if (num.asNumeric() == i)
				return num;
		}

		return null;
	}
}
