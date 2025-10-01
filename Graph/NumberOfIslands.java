/**
 *  Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 *  return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * **/

public class  NumberOfIslands {

		public int numIslands(char[][] grid) {
			if (grid == null || grid.length == 0) return 0;

			int rows = grid.length;
			int cols = grid[0].length;
			int count = 0;

			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					if (grid[r][c] == '1') {
						dfs(grid, r, c);
						count++;
					}
				}
			}

			return count;
		}

		private void dfs(char[][] grid, int r, int c) {
			int rows = grid.length;
			int cols = grid[0].length;

			// Check boundaries and if the cell is water ('0')
			if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
				return;
			}

			// Mark current cell as visited
			grid[r][c] = '0';

			// Explore all 4 directions (up, down, left, right)
			dfs(grid, r - 1, c); // up
			dfs(grid, r + 1, c); // down
			dfs(grid, r, c - 1); // left
			dfs(grid, r, c + 1); // right
		}

}