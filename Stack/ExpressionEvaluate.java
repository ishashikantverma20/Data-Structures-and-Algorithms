public class ExpressionEvaluate {

		public static int evaluate(String s) {
			Stack<Integer> nums = new Stack<>();
			Stack<Character> ops = new Stack<>();

			int n = s.length();
			int i = 0;

			while (i < n) {
				char c = s.charAt(i);

				if (c == ' ') {
					i++;
					continue;
				}

				if (c == '(') {
					ops.push(c);
					i++;
				} else if (Character.isDigit(c)) {
					int num = 0;
					while (i < n && Character.isDigit(s.charAt(i))) {
						num = num * 10 + (s.charAt(i) - '0');
						i++;
					}
					nums.push(num);
				} else if (c == ')') {
					// Evaluate until matching '('
					while (ops.peek() != '(') {
						nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
					}
					ops.pop(); // pop '('
					i++;
				} else if (isOperator(c)) {
					// Evaluate higher or equal precedence ops before pushing current op
					while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
						nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
					}
					ops.push(c);
					i++;
				} else {
					// Invalid char (optional: throw exception)
					i++;
				}
			}

			// Evaluate remaining ops
			while (!ops.isEmpty()) {
				nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
			}

			return nums.pop();
		}

		private static boolean isOperator(char c) {
			return c == '+' || c == '-' || c == '*' || c == '/';
		}

		private static int precedence(char op) {
			if (op == '+' || op == '-') return 1;
			if (op == '*' || op == '/') return 2;
			return 0;
		}

		private static int applyOp(char op, int b, int a) {
			switch (op) {
				case '+': return a + b;
				case '-': return a - b;
				case '*': return a * b;
				case '/':
					if (b == 0) throw new ArithmeticException("Division by zero");
					return a / b;
			}
			return 0;
		}

		public static void main(String[] args) {
			String expr = "(2*5+7)";
			System.out.println("Result: " + evaluate(expr));  // Output: 17
		}

}