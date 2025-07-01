public class TwoSumSortedII {
	/**
	 *  Input: numbers = [2,7,11,15], target = 9
	 * Output: [1,2]
	 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
	 *
	 * **/

	public int[] twoSum(int[] numbers, int target) {
		int[] indice = new int[2];

		// edge case
		if (num == null || num.length < 2) return indice;

		int left = 0, right = num.length - 1;

		// running pointers from left and right and checking sum of both
		// if sum > right decreasae
		// if sum < left increase
		// if sum = found the pairs

		while (left < right) {
			int sum = num[left] + num[right];

			if (sum == target) {
				indice[0] = left + 1;
				indice[1] = right + 1;
				break;
			} else if (sum > target) {
				right --;
			} else {
				left ++;
			}
		}

		return indice;
	}
}