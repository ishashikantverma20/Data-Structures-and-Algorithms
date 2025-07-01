public class TFourSum {

	/***
	 *
	 *  Input: nums = [1,0,-1,0,-2,2], target = 0
	 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
	 *
	 * **/

	// adding one extra for loop on 3 sum approach
	// else it is 3 sum with 2 pointer
	// TC- O{N^3)

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int n = nums.length;

		for (int i = 0; i < n - 3; i++) {
			// cases like {1 , 1 , 2, 3}
			if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates

			for (int j = i + 1; j < n - 2; j++) {
				// cases like {1 , 1 , 2, 3}
				if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates

				int left = j + 1;
				int right = n - 1;

				while (left < right) {
					long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

					if (sum == target) {
						result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
						left++;
						right--;

						// Skip duplicates
						while (left < right && nums[left] == nums[left - 1]) left++;
						while (left < right && nums[right] == nums[right + 1]) right--;
						
					} else if (sum < target) {
						left++;
					} else {
						right--;
					}
				}
			}
		}

		return result;
	}



}