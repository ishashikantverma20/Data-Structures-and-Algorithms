public class SimpleFractionToDecimal {

		public static String fractionToDecimal(String fraction) {
			String[] parts = fraction.split("/");
			long numerator = Long.parseLong(parts[0]);
			long denominator = Long.parseLong(parts[1]);

			if (numerator == 0) return "0";

			StringBuilder result = new StringBuilder();

			// Handle sign
			if ((numerator < 0) ^ (denominator < 0)) {
				result.append("-");
			}

			numerator = Math.abs(numerator);
			denominator = Math.abs(denominator);

			// Append integer part
			long integerPart = numerator / denominator;
			result.append(integerPart);

			long remainder = numerator % denominator;
			if (remainder == 0) {
				return result.toString();
			}

			result.append(".");

			// Map to store remainder and corresponding index in result
			Map<Long, Integer> remainderPositions = new HashMap<>();

			while (remainder != 0) {
				if (remainderPositions.containsKey(remainder)) {
					int index = remainderPositions.get(remainder);
					result.insert(index, "(");
					result.append(")");
					break;
				}
				remainderPositions.put(remainder, result.length());
				remainder *= 10;
				result.append(remainder / denominator);
				remainder %= denominator;
			}

			return result.toString();
		}

		public static void main(String[] args) {
			System.out.println(fractionToDecimal("2/3"));    // Output: 0.(6)
			System.out.println(fractionToDecimal("-2/5"));   // Output: -0.4
			System.out.println(fractionToDecimal("1/2"));    // Output: 0.5
			System.out.println(fractionToDecimal("4/333"));  // Output: 0.(012)
		}

}