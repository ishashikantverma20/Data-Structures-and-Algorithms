public class JumpGameII {

	/**
	 *  nums = [2, 3, 1, 1, 4]
	 *  output - 2
	 *
	 *  here following greedy approach, how far we can go from current position.
	 *
	 *  [2, 3, 1, 1, 4]
	 *   0  1  2  3  4
	 *
	 *  from 0th index, we can go for index 1 val 3 and index 2 val 1
	 *  and current end is one block end  and next block start, so before moving increasing jump and moving current end to next
	 *  farthest point block end.
	 * **/

	public int jump(int[] nums) {
		int jumps = 0;
		int currentEnd = 0;
		int maximumReach = 0;

		for (int i = 0; i < nums.length - 1; i++) {
			// i + nums[i] - as will lead to next index in array
			maximumReach = Math.max(maximumReach, i + nums[i]);

			if (i == currentEnd) {
				jumps++;
				currentEnd = maximumReach;
			}
		}
		return jumps;
	}
}
