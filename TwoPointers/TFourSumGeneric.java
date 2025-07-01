public class TFourSumGeneric {

	/**
	 * Input: nums = [1,0,-1,0,-2,2], target = 0
	 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
	 *
	 *
	 * ***/

	// TC -	O(n^(k-1))
	// SC - O(k) - K sum
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		return kSum(nums, 0, 4, target);
	}

	private List<List<Integer>> kSum(int[] nums, int start, int k, long target) {
		List<List<Integer>> res = new ArrayList<>();
		if (start == nums.length) return res;

		// when k == 2 it will work as 2 sum
		if (k == 2) return twoSum(nums, start, target);

		for (int i = start; i < nums.length - k + 1; i++) {
			if (i > start && nums[i] == nums[i - 1]) continue;

			// here increasing i and fixing one variable
			// and for next level decreaing k
			for (List<Integer> subset : kSum(nums, i + 1, k - 1, target - nums[i])) {
				subset.add(0, nums[i]);
				res.add(subset);
			}
		}

		return res;
	}

	private List<List<Integer>> twoSum(int[] nums, int start, long target) {
		List<List<Integer>> res = new ArrayList<>();
		int left = start, right = nums.length - 1;

		while (left < right) {
			long sum = nums[left] + nums[right];
			if (sum == target) {
				res.add(Arrays.asList(nums[left], nums[right]));
				left++; right--;
				while (left < right && nums[left] == nums[left - 1]) left++;
				while (left < right && nums[right] == nums[right + 1]) right--;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return res;
	}
}