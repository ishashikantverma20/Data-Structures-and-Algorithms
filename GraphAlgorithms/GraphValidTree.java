/**
 *  Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 *  write a function to check whether these edges make up a valid tree.
 *
 *  Input:
 *  n = 5
 * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 *
 * Output:
 * true
 *
 *
 * ***/
class DSU {
		int[] Parent, Size;
		int comps;

		public DSU(int n) {
			comps = n;
			Parent = new int[n + 1];
			Size = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				Parent[i] = i;
				Size[i] = 1;
			}
		}

		public int find(int node) {
			if (Parent[node] != node) {
				Parent[node] = find(Parent[node]);
			}
			return Parent[node];
		}

		public boolean union(int u, int v) {
			int pu = find(u), pv = find(v);
			if (pu == pv) return false;
			if (Size[pu] < Size[pv]) {
				int temp = pu;
				pu = pv;
				pv = temp;
			}
			comps--;
			Size[pu] += Size[pv];
			Parent[pv] = pu;
			return true;
		}

		public int components() {
			return comps;
		}
}


public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		// A valid tree must have exactly n-1 edges
		if (edges.length > n - 1) {
			return false;
		}

		DSU dsu = new DSU(n);
		for (int[] edge : edges) {
			if (!dsu.union(edge[0], edge[1])) {
				return false;
			}
		}

		// beacuse in end there is only 1 component should be left, so return true
		return dsu.components() == 1;
	}
}

//------------------------------------------------------------------

// DFS Approach
 class Solution {
	public boolean validTree(int n, int[][] edges) {
		// A valid tree must have exactly n-1 edges
		if (edges.length > n - 1) {
			return false;
		}

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		Set<Integer> visit = new HashSet<>();
		if (!dfs(0, -1, visit, adj)) {
			return false;
		}

		return visit.size() == n;
	}

	private boolean dfs(int node, int parent, Set<Integer> visit,
	                    List<List<Integer>> adj) {
		if (visit.contains(node)) {
			return false;
		}

		visit.add(node);
		for (int nei : adj.get(node)) {
			if (nei == parent) {
				continue;
			}
			if (!dfs(nei, node, visit, adj)) {
				return false;
			}
		}
		return true;
	}
}