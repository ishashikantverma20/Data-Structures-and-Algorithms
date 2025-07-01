public class MinimumSizeSubarraySum {

	/**
	 *  target = 7, nums = [2,3,1,2,4,3]
	 *                          ^   ^
	 *                              ^ ^ -  minimum
	 * Output: 2
	 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
	 *
	 * **/
	public static int minSubArrayLen(int target, int[] nums) {
		int left = 0;
		int sum = 0;
		int minLength = Integer.MAX_VALUE;

		for (int right = 0; right < nums.length; right++) {
			// adding number to increase window length
			sum += nums[right];

			// shrink window if sum is grater than target or equal
			while (sum >= target) {
				// calculate min length
				minLength = Math.min(minLength, right - left + 1);
				sum -= nums[left];
				left++;
			}
		}

		// if no minimum length found to check
		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
}
