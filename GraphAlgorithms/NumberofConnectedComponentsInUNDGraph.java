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
	class DSU {
		int[] parent;
		int[] rank;

		public DSU(int n) {
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int node) {
			int cur = node;
			while (cur != parent[cur]) {
				parent[cur] = parent[parent[cur]];
				cur = parent[cur];
			}
			return cur;
		}

		public boolean union(int u, int v) {
			int pu = find(u);
			int pv = find(v);
			if (pu == pv) {
				return false;
			}
			if (rank[pv] > rank[pu]) {
				int temp = pu;
				pu = pv;
				pv = temp;
			}
			parent[pv] = pu;
			rank[pu] += rank[pv];
			return true;
		}
	}

	 class SolutionI {
		public int countComponents(int n, int[][] edges) {
			DSU dsu = new DSU(n);
			int res = n;
			for (int[] edge : edges) {
				if (dsu.union(edge[0], edge[1])) {
					res--;
				}
			}
			return res;
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