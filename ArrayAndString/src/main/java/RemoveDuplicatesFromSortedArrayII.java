public class RemoveDuplicatesFromSortedArrayII {

	public int removeDuplicates(int[] nums) {
        /**
         *  simple is that r pointer will move till 2 elements are same. but when r & r + 1 is different then find minimum gap.
         *  l pointer will be move that much gap, Max l pointer movement will be 2.
         *  after one loop increment r pointer to move ahead adn point next element.
         *  [0,0,1,1,1,1,2,3,3]
         * 	   l       r
         * */

		int l = 0, r = 0;

		while(r  < nums.length) {
			int count  = 1;

			while(r +  1 < nums.length && nums[r] == nums[r+1]) {
				r++;
				count++;
			}

			int gap = Math.min(2, count);

			while(gap-- > 0) {
				nums[l] = nums[r];
				l++;
			}

			r = r + 1;
		}

		return l;
	}
}
