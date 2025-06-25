public class SpiralMatrixIII {

	/**
	 * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
	 *  [
	 *   [30, 25, 16, 7, 8,   9]
	 *   [29, 24, 15, 6, 1,   2]
	 *   [28, 23, 14, 5, 4,   3]
	 *   [27, 22, 13, 12, 11, 10]
	 *   [26, 21, 20, 19, 18, 17]
	 *
	 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
	 *
	 *
	 *
	 * **/
	public List<int[]> spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
		List<int[]> result = new ArrayList<>();
		int count = 0;
		int step = 1;

		while (count < rows * cols) {
			// Move right
			for (int i = 0; i < step; i++) {
				if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
					result.add(new int[]{rStart, cStart});
					count++;
				}
				cStart++;
			}

			// Move down
			for (int i = 0; i < step; i++) {
				if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
					result.add(new int[]{rStart, cStart});
					count++;
				}
				rStart++;
			}

			step++;

			// Move left
			for (int i = 0; i < step; i++) {
				if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
					result.add(new int[]{rStart, cStart});
					count++;
				}
				cStart--;
			}

			// Move up
			for (int i = 0; i < step; i++) {
				if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
					result.add(new int[]{rStart, cStart});
					count++;
				}
				rStart--;
			}

			step++;
		}

		return result;
	}
}