/**
 *  You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 *
 * We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 *
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *  Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid is filled with water.
 * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 *
 *
 * **/
public class NumberOfIslandsII {

	class Solution {
		public List<Integer> numIslands2(int m, int n, int[][] positions) {
			List<Integer> result = new ArrayList<>();
			UnionFind uf = new UnionFind(m, n);

			int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};

			for (int[] pos : positions) {
				int r = pos[0], c = pos[1];
				int idx = r * n + c;
				if (uf.isLand(idx)) {
					result.add(uf.getCount()); // Skip duplicate land addition
					continue;
				}

				uf.addLand(idx);

				for (int[] dir : directions) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					int nIdx = nr * n + nc;

					if (nr >= 0 && nr < m && nc >= 0 && nc < n && uf.isLand(nIdx)) {
						uf.union(idx, nIdx);
					}
				}

				result.add(uf.getCount());
			}

			return result;
		}

		class UnionFind {
			int[] parent;
			int[] size;
			int count;
			boolean[] isLand;

			public UnionFind(int m, int n) {
				int len = m * n;
				parent = new int[len];
				size = new int[len];
				isLand = new boolean[len];
				count = 0;

				for (int i = 0; i < len; i++) {
					parent[i] = i;
					size[i] = 1;
					isLand[i] = false;
				}
			}

			public boolean isLand(int i) {
				return isLand[i];
			}

			public void addLand(int i) {
				if (!isLand[i]) {
					isLand[i] = true;
					count++;
				}
			}

			public int find(int x) {
				if (parent[x] != x) {
					parent[x] = find(parent[x]); // Path compression
				}
				return parent[x];
			}

			public void union(int x, int y) {
				int rootX = find(x);
				int rootY = find(y);

				if (rootX == rootY) return;

				// Union by size
				if (size[rootX] < size[rootY]) {
					parent[rootX] = rootY;
					size[rootY] += size[rootX];
				} else {
					parent[rootY] = rootX;
					size[rootX] += size[rootY];
				}

				count--; // Reduce number of islands
			}

			public int getCount() {
				return count;
			}
		}
	}

}