/**
 * Design a stack that supports increment operations on its elements.
 *
 * Implement the CustomStack class:
 *
 * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
 * void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
 * int pop() Pops and returns the top of the stack or -1 if the stack is empty.
 * void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, increment all the elements in the stack.
 *
 * Input
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * Output
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 *
 * **/
public class DesignAStackWithIncrementOperation {

	class CustomStack {

		int size = 0;
		int[] stack;
		int curr = 0;

		public CustomStack(int maxSize) {
			stack = new int[maxSize];
			size = maxSize;
		}

		public void push(int x) {
			if (curr < size) {
				stack[curr] = x;
				curr++;
			}
			// else do nothing (stack is full)
		}

		public int pop() {
			if (curr == 0) {
				return -1;  // stack empty
			}
			curr--;
			return stack[curr];
		}

		public void increment(int k, int val) {
			int limit = Math.min(k, curr);
			for (int j = 0; j < limit; j++) {
				stack[j] += val;
			}
		}
	}
}