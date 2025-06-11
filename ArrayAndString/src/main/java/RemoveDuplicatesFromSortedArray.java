public class RemoveDuplicatesFromSortedArray {
	public int removeDuplicates(int[] nums) {
		// [0,0,1,1,1,2,2,3,3,4]
		//  i j

		// 1 1 2
		// i   j

        /**
         *  if i & j pointer value same, then move j pointer
         *  if i & j pointer value diff, then first increment i and assign j pointer value to i pointer value
         *  return i + 1, as i is 0 based index, but we need count how many duplicates were present there in array.
         * */

		int i = 0;
		int j = 0;
		int len = nums.length;

		while(j < len) {
			if(nums[i] != nums[j]) {
				i++;
				nums[i] = nums[j];
			}
			j++;
		}

		return i+1;
	}
}
