public class ThreeSum {
	/***
	 *  Input: nums = [-1,0,1,2,-1,-4]
	 * Output: [[-1,-1,2],[-1,0,1]]
	 * Explanation:
	 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
	 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
	 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
	 * The distinct triplets are [-1,0,1] and [-1,-1,2].
	 * Notice that the order of the output and the order of the triplets does not matter.
	 *
	 *
	 * **/

	//BF solution is use 3 for loops - TC O(N^3)
	// space could be O(N) - to sort array
	public List<List<Integer>> threeSumBF(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();
		int n = nums.length;
		Arrays.sort(nums); // To help with duplicate checking
		for (int i = 0; i < n - 2; i++) {
			for (int j = i+1; j < n - 1; j++) {
				for (int k = j+1; k < n; k++) {
					// 0 could be any target
					if (nums[i] + nums[j] + nums[k] == 0) {
						result.add(Arrays.asList(nums[i], nums[j], nums[k]));
					}
				}
			}
		}
		return new ArrayList<>(result);
	}

	public List<List<Integer>> threeSum(int[] nums) {


		// Better solution with TC O(N^2) and space could be O(N) - to sort array

		// sort the array
		// fix one element in each iteration
		// now it worked as 2 pointer approach

		List<List<Integer>> result = new ArrayList<>();
		// sort the array
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {

			// if current and next element is similar then skip this
			if (i > 0 && nums[i] == nums[i - 1]) continue;

			int left = i + 1;
			int right = nums.length - 1;

			while (left < right) {
				// nums[i] is fixed
				// nums[left] and nums[right] will vary
				int sum = nums[i] + nums[left] + nums[right];

				// 0 could be any target
				if (sum == 0) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					left++;
					right--;

					// Skip duplicates
					while (left < right && nums[left] == nums[left - 1]) left++;
					while (left < right && nums[right] == nums[right + 1]) right--;

				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}

		return result;

	}
}