public class SpiralMatrixI {

	/**
	 *  Input: matrix = [[1,2,3]
	 *                  ,[4,5,6]
	 *                  ,[7,8,9]]
	 *
	 *  Output: [1,2,3,6,9,8,7,4,5]
	 *
	 *
	 * **/
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();

		if (matrix.length == 0)
			return result;

		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;

		while (left <= right && top <= bottom) {
			// Traverse from left to right
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
			top++;

			// Traverse downwards
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][right]);
			}
			right--;

			// Traverse from right to left
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					result.add(matrix[bottom][i]);
				}
				bottom--;
			}

			// Traverse upwards
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					result.add(matrix[i][left]);
				}
				left++;
			}
		}

		return result;
	}
}