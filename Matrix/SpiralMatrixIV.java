public class SpiralMatrixIV {

	/**
	 * Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
	 *  [
	 *   [3 -> 0 -> 2 -> 6 -> 8]
	 *   [5 -> 0 -> -1 -> -1 -> 1]
	 *   [5 -> 2 -> 4 -> 9 -> 7]
	 *  ]
	 *
	 * Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
	 * Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
	 * **/
	public int[][] spiralMatrix(int m, int n, ListNode head) {
		int[][] matrix = new int[m][n];

		// Fill the matrix with -1 initially
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = -1;
			}
		}

		int top = 0, bottom = m - 1;
		int left = 0, right = n - 1;

		while (left <= right && top <= bottom) {
			// Traverse from left to right
			for (int i = left; i <= right; i++) {
				if (head != null) {
					matrix[top][i] = head.val;
					head = head.next;
				}
			}
			top++;

			// Traverse top to bottom
			for (int i = top; i <= bottom; i++) {
				if (head != null) {
					matrix[i][right] = head.val;
					head = head.next;
				}
			}
			right--;

			// Traverse right to left
			for (int i = right; top <= bottom && i >= left; i--) {
				if (head != null) {
					matrix[bottom][i] = head.val;
					head = head.next;
				}
			}
			bottom--;

			// Traverse bottom to top
			for (int i = bottom; left <= right && i >= top; i--) {
				if (head != null) {
					matrix[i][left] = head.val;
					head = head.next;
				}
			}
			left++;
		}

		return matrix;
	}
}