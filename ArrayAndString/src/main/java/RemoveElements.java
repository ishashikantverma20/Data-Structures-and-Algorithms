public class RemoveElements {

	public int removeElement(int[] nums, int val) {
		// 3 2 2 3  val = 3
		// i
		//   j

	   // if j pointer value not matched with val, then put j pointer value to i pointer and increase i pointer
	  // j pointer will always move

		int j = 0;
		int i = 0;
		int len  = nums.length;

		while(j < len) {
			if(nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
			j++;
		}

		return i;
	}
}
