public class SpiralMatrixII {
	/**
	 *  Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
	 *
	 *  Input: n = 3
	 * Output: [[1,2,3],
	 *          [8,9,4],
	 *          [7,6,5]]
	 * **/

	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];

		int left = 0, right = n - 1;
		int top = 0, bottom = n - 1;
		int count = 1;

		while (left <= right && top <= bottom) {
			// Left to right
			for (int i = left; i <= right; i++) {
				matrix[top][i] = count++;
			}
			top++;

			// Top to bottom
			for (int i = top; i <= bottom; i++) {
				matrix[i][right] = count++;
			}
			right--;

			// Right to left
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					matrix[bottom][i] = count++;
				}
				bottom--;
			}

			// Bottom to top
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					matrix[i][left] = count++;
				}
				left++;
			}
		}

		return matrix;
	}


}