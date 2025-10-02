/**
 *  You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * **/
public class JumpGameI {
	public boolean canJump(int[] nums) {
		boolean[] dp = new boolean[nums.length];
		dp[0] = true;

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && j + nums[j] >= i) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[nums.length - 1];
	}

}