/**
 *  An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.
 *
 * Given the grid grid represented as a string array, return the number of regions.
 *
 * Note that backslash characters are escaped, so a '\' is represented as '\\'.
 *
 *  Input: grid = [" /","/ "]  -> "_/", "_/" -> "_" - empty space
 *  Output: 2
 *
 *   int[][] matrix = {
 *     {0, 0, 1,   1, 0, 0},
 *     {0, 1, 0,   0, 1, 0},
 *     {1, 0, 0,   0, 0, 1},
 *     {1, 0, 0,   0, 0, 1},
 *     {0, 1, 0,   0, 1, 0},
 *     {0, 0, 1,   1, 0, 0}
 *  };
 *
 *  charachter representation
 *
 *  '/'  -  0 0 1
 *          0 1 0
 *          1 0 0
 *
 *  '\'  -  1 0 0
 *          0 1 0
 *          0 0 1
 *
 *  ' ' -   0 0 0
 *          0 0 0
 *          0 0 0
 *
 *
 * **/
public class RegionsCutBySlashes {


	// DFS
	// Flood fill to count regions
	void dfs(int[][] matrix, int i, int j) {

		int rows = matrix.length;
		int cols = matrix[0].length;

		// Out of bounds or already visited
		if (i < 0 || j < 0 || i >= rows || j >= cols || matrix[i][j] == 1) {
			return;
		}

		// Mark as visited
		matrix[i][j] = 1;

		// Explore all 4 directions
		dfs(matrix, i - 1, j); // up
		dfs(matrix, i + 1, j); // down
		dfs(matrix, i, j - 1); // left
		dfs(matrix, i, j + 1); // right
	}

	public int regionsBySlashes(String[] grid) {
		int rows = grid.length;
		int cols = grid[0].length();

		int[][] matrix = new int[rows * 3][cols* 3];

		// Draw slashes on the expanded grid
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				char c = grid[i].charAt(j);

				if (c == '/') {
					matrix[i * 3][j * 3 + 2] = 1;
					matrix[i * 3 + 1][j * 3 + 1] = 1;
					matrix[i * 3 + 2][j * 3] = 1;
				} else if (c == '\\') {
					matrix[i * 3][j * 3] = 1;
					matrix[i * 3 + 1][j * 3 + 1] = 1;
					matrix[i * 3 + 2][j * 3 + 2] = 1;
				}
			}
		}

		int regions = 0;

		// Flood fill to count regions
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					dfs(matrix, i, j);
					regions++;
				}
			}
		}

		return regions;
	}

}