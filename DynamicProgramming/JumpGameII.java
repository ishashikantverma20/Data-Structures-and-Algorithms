/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index
 *
 *
 * **/
public class JumpGameII {
	public int jump(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		for (int i = 1; i < n; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < n; i++) {
			int furthest = Math.min(i + nums[i], n - 1);
			for (int j = i + 1; j <= furthest; j++) {
				if (dp[i] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[i] + 1);
				}
			}
		}
		return dp[n - 1];
	}


}