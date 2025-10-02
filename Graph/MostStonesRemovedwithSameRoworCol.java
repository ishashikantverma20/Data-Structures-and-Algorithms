/**
 *
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 *
 *  Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 *
 * Row\Col  0   1   2
 *      +---+---+---+
 *   0  | ● | ● |   |
 *      +---+---+---+
 *   1  | ● |   | ● |
 *      +---+---+---+
 *   2  |   | ● | ● |
 *      +---+---+---+
 *
 * Example:
 *
 *  Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 *
 * Core Idea:
 * 1. Think this locations as graph node, and union them which is linked
 * 2. Union them which is linked
 * 3. Subtract union components from total components given
 *
 * Like above case
 *  [0,0],[0,2],[2,0],[2,2] -   0 <- 2 - 1 component
 *  [1,1]                   -   1      - 1 component
 *
 *  so they made 2 component and total was 5 component, so 5-2 = 3 return
 *
 *
 * **/
public class MostStonesRemovedwithSameRoworCol {


	class Solution {
		public int removeStones(int[][] stones) {
			UnionFind uf = new UnionFind();

			for(int[] stone : stones) {
				int row = stone[0];
				int col = stone[1] + 100000;
				uf.union(row, col);
			}

			return stones.length - uf.getCount();
		}
	}

	public class UnionFind {
		private Map<Integer, Integer> parent;
		private int count = 0; // no. of component

		public UnionFind() {
			parent = new HashMap();
		}

		// Path compression
		public int find(int x) {

			if(!parent.containsKey(x)) {
				parent.put(x, x);
				count++; // new component added, so ++
			}

			if (parent.get(x) != x) {
				parent.put(x, find(parent.get(x)));  // find parent of x and set it
			}
			return parent.get(x);
		}

		// Union
		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX != rootY) {

				parent.put(rootX,rootY);
				count--; // union done, decrease one component
			}
		}

		// Get number of disjoint sets
		public int getCount() {
			return count;
		}
	}


	// DFS -------------------

	class Solution {
		public int removeStones(int[][] stones) {
			int n = stones.length;
			boolean[] visited = new boolean[n];

			// Build graph: connect stones if they share row or column
			List<List<Integer>> graph = new ArrayList<>();
			for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

			for (int i = 0; i < n; i++) {
				// undirected graph - so matrix symmetric cover upper triangle, s0 j = i + 1
				for (int j = i + 1; j < n; j++) {
					if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
						graph.get(i).add(j);
						graph.get(j).add(i);
					}
				}
			}

			int components = 0;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					dfs(i, graph, visited);
					components++;
				}
			}

			return n - components;
		}

		private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
			visited[node] = true;
			for (int nei : graph.get(node)) {
				if (!visited[nei]) {
					dfs(nei, graph, visited);
				}
			}
		}
	}

}