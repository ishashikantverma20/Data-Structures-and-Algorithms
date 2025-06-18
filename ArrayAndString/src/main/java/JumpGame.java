public class JumpGame {

	/**
	 *  Input: nums = [2,3,1,1,4]
	 * Output: true
	 *
	 * Input: nums = [3,2,1,0,4]
	 * Output: false
	 *
	 * TC - O(N)
	 * SC - O(1)
	 * **/

	public static void main(String[] args) {
		int[] nums = {3,2,1,0,4};
		canJump(nums);

	}

	public static boolean canJump(int[] nums) {
		int maximumReach = 0;

		for (int i = 0; i < nums.length; i++) {
			// {3,2,1,0,4} this check stop, once max reach end, from here onwards i value increase and if its grater than max reach so return false
			// {3,2,1,1,1,1,4} - here i will increase
			if (i > maximumReach) {
				return false;
			}
			// i + nums[i] - as adding index to value, so we know till which we can reach from here
			// i = 2, nums[i] = 2, so we can reach till 4th index
			maximumReach = Math.max(maximumReach, i + nums[i]);
		}

		return true;
	}
}
