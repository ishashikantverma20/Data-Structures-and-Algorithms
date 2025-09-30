/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of province
 *
 *
 * int[][] isConnected = {
 *     {1, 1, 0},
 *     {1, 1, 0},
 *     {0, 0, 1}
 * };
 * // Output: 2
 *
 *
 * **/

public class NumberofProvinces {

	// Union FInd
	/**
	 *  Each city is a node
	 *  Using union find to grouped connected cities
	 *  count the number of unique sets (provinces)
	 * */
	class Solution {
		public int findCircleNum(int[][] isConnected) {
			int n = isConnected.length;
			UnionFind uf = new UnionFind(n);

			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (isConnected[i][j] == 1) {
						uf.union(i, j);
					}
				}
			}

			return uf.getCount();
		}

		class UnionFind {
			int[] parent;
			int[] size;
			int count;

			public UnionFind(int n) {
				parent = new int[n];
				size = new int[n];
				count = n;

				for (int i = 0; i < n; i++) {
					parent[i] = i;
					size[i] = 1;
				}
			}

			public int find(int x) {
				if (parent[x] != x) {
					parent[x] = find(parent[x]);  // Path compression
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

				count--;
			}

			public int getCount() {
				return count;
			}
		}
	}


	// DFS --------------------------------------------------------
	/**
	 *  Traverse each city using DFS
	 *  Count how many times DFS is called
	 * */
	class Solution {
		public int findCircleNum(int[][] isConnected) {
			int n = isConnected.length;
			boolean[] visited = new boolean[n];
			int count = 0;

			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					dfs(isConnected, visited, i);
					count++;
				}
			}

			return count;
		}

		private void dfs(int[][] isConnected, boolean[] visited, int i) {
			visited[i] = true;

			for (int j = 0; j < isConnected.length; j++) {
				if (isConnected[i][j] == 1 && !visited[j]) {
					dfs(isConnected, visited, j);
				}
			}
		}
	}


}