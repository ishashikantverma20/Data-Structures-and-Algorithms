import java.util.Arrays;

public class MergeSortedArray {
	public static void main(String[] args) {
		// Brute force is add n elements of nums2 in nums1 after m element and sort the array.
		// stuck there how to move elements so make space and move elements and replace 0.
		// we don't need to traverse from start, need to traverse both array from end and maintain a pointer which fill values.

		// {1,2,3,0,0,0}
		//      i     p
		// {2,5,6}
		//      j

		int[] nums1 = {1,2,3,0,0,0};
		int m = 3;
		int[] nums2 = {2,5,6};
		int n = 3;

		merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));



	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int p = m + n - 1;

		while (j >= 0) { // j is here because once 2nd array element end we dont need to run this loop
			if(i >= 0 && nums1[i] > nums2[j]) {
				nums1[p--] = nums1[i--];
			} else {
				nums1[p--] = nums2[j--];
			}
		}

	}

}
