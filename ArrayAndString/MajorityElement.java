public class MajorityElement {
	public int majorityElement(int[] nums) {
		/**
		 1. Brute force soln: create HashMap and store elements and there count. Iterate hashmap and comapre count with n/2 and return element which satiesfies this.

		 2. Sort the array and linearly count element and return the result which is > n/2.

		 3. Moore Voting Algorithm: Its only work when there is a majority element present.
		 Majority element will be max, that's why count is decreasing when num and candidate does not match.
		 And when count is 0, its means current element is not a majority element so we need to update new element as  a Majority element.

		 */

		int count = 0, candidate = 0;

		for(int num : nums) {

			if(count == 0) {
				candidate = num;
			}

			if(num == candidate) {
				count++;
			} else {
				count--;
			}
		}
		return candidate;
	}
}
