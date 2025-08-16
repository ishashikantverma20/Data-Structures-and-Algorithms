public class RotateArray {
	public void rotate(int[] nums, int k) {
		/**
		 1. Brute force -iterate till k times, and placed each element to correct position. TC -  (K*N)

		 2. take a new array, and place k last element -placed in array first, and then placed other elements after that in array
		 TC - (N)
		 SC - (N)

		 3. Reverse the entire array,
		    then reverse first k elements
		    then reverse after n-k elements

		 nums = [1,2,3,4,5,6,7], k = 3

		 first reverse -  7 6 5 4 3 2 1
		 second reverse - 5 6 7 4 3 2 1
		 third reverse -  5 6 7 1 2 3 4

		 Output: [5,6,7,1,2,3,4]
		 */

		int len = nums.length;

		if (k > len) {  // if k iteration is greater than len, then fit it to 0 and len
		    k = k % len;
		}

		reverse(nums, 0,  len -1);

		reverse(nums, 0, k - 1);

		reverse(nums, k, len - 1);

	}

	public void reverse(int[] nums, int l, int r) {
		int left = l;
		int right = r;

		while(left < right){
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}
	}
}
