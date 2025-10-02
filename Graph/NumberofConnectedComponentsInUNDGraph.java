/**
 *  Number of Connected Components in an Undirected Graph
 *
 *  There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is an edge between node a and node b in the graph.
 *
 * The nodes are numbered from 0 to n - 1.
 *
 * Return the total number of connected components in that graph.
 *
 * Input:
 * n=6
 * edges=[[0,1], [1,2], [2,3], [4,5]]
 *
 *    o -- 1     4
 *         |     |
 *         2     5
 * Output:
 * 2
 *
 * **/
public class NumberofConnectedComponentsInUNDGraph {

	// Union Find
	class SolutionI {
		public int countComponents(int n, int[][] edges) {
			UnionFind uf = new UnionFind(n);

			for (int[] edge : edges) {
				uf.union(edge[0], edge[1]);
			}

			return uf.getCount();
		}
	}

	class UnionFind {
		int[] parent;
		int[] size;
		int count;

		public UnionFind(int n) {
			parent = new int[n];
			size = new int[n];
			// n = number of components in starting
			count = n;

			for (int i = 0; i < n; i++) {
				parent[i] = i;  // each node is its own parent
				size[i] = 1;    // initial rank
			}
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);  // path compression
			}
			return parent[x];
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX == rootY) return;  // already in the same component

			// Union by size
			if (size[rootX] < size[rootY]) {
				parent[rootX] = rootY;
				size[rootY] += size[rootX];
			} else {
				parent[rootY] = rootX;
				size[rootX] += size[rootY];
			}

			count--;  // Decrease component count after a union
		}

		public int getCount() {
			return count;
		}
	}




	// DFS ------------------------------------------------------------------
	 class SolutionII {
		public int countComponents(int n, int[][] edges) {
			List<List<Integer>> adj = new ArrayList<>();
			boolean[] visit = new boolean[n];
			for (int i = 0; i < n; i++) {
				adj.add(new ArrayList<>());
			}
			for (int[] edge : edges) {
				adj.get(edge[0]).add(edge[1]);
				adj.get(edge[1]).add(edge[0]);
			}

			int res = 0;
			for (int node = 0; node < n; node++) {
				if (!visit[node]) {
					dfs(adj, visit, node);
					res++;
				}
			}
			return res;
		}

		private void dfs(List<List<Integer>> adj, boolean[] visit, int node) {
			visit[node] = true;
			for (int nei : adj.get(node)) {
				if (!visit[nei]) {
					dfs(adj, visit, nei);
				}
			}
		}
	}
}