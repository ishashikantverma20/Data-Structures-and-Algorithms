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
 * Valid Tree:
 *  a. No cycle
 *  b. No diconnected nodes
 *  c. TO connect N nodes, there should be alteast N-1 connection present
 *
 * ***/
class UnionFind {
	private int[] parent;
	private int[] rank;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);  // path compression
		}
		return parent[x];
	}

	public boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY) {
			return false;  // cycle detected
		}

		// union by rank
		if (rank[rootX] > rank[rootY]) {
			parent[rootY] = rootX;
		} else if (rank[rootX] < rank[rootY]) {
			parent[rootX] = rootY;
		} else {
			parent[rootY] = rootX;
			rank[rootX]++;
		}
		return true;
	}
}

public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		if (edges.length != n - 1) {
			return false;  // tree must have exactly n-1 edges
		}

		UnionFind uf = new UnionFind(n);

		for (int[] edge : edges) {
			if (!uf.union(edge[0], edge[1])) {
				return false;  // cycle detected
			}
		}

		return true;
	}

	// Example usage
	public static void main(String[] args) {
		Solution sol = new Solution();
		int n = 5;
		int[][] edges = {{0,1}, {0,2}, {0,3}, {1,4}};
		System.out.println(sol.validTree(n, edges));  // Output: true
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