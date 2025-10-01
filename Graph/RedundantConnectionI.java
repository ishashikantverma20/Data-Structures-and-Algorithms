/**
 *  In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
 *
 *  1 - 2
 *  | /
 *  3
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 *
 * **/
public class RedundantConnectionI {

	// Union-Find
	class Solution {
		public int[] findRedundantConnection(int[][] edges) {
			int len = edges.length;
			UnionFind uf = new UnionFind(len);
			int[] result = new int[2];

			for(int[] edge : edges) {
				if(!uf.union(edge[0], edge[1])) {
					result[0] = edge[0];
					result[1] = edge[1];
				}
			}
			return result;
		}
	}

	 class UnionFind {
		private int[] parent;
		private int[] size;   // optional
		private int count;  // Number of disjoint sets

		public UnionFind(int n) {
			parent = new int[n+1];
			size = new int[n+1];
			count = n;  // Initially, each node is its own set

			for (int i = 0; i < n; i++) {
				parent[i] = i;  // Each element is its own parent
				size[i] = 1;    // Each set initially has size 1
			}
		}

		// Path compression
		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);  // Flatten the tree
			}
			return parent[x];
		}

		// Union by size
		public boolean union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX == rootY) {
				return false;
			}

			// Attach smaller tree to larger
			if (size[rootX] < size[rootY]) {
				parent[rootX] = rootY;
				size[rootY] += size[rootX];
			} else {
				parent[rootY] = rootX;
				size[rootX] += size[rootY];
			}

			count--;  // Merged two sets

			return true;
		}

		// Check if two elements are connected
		public boolean connected(int x, int y) {
			return find(x) == find(y);
		}

		// Get the size of the set containing x
		public int getSize(int x) {
			return size[find(x)];
		}

		// Get number of disjoint sets
		public int getCount() {
			return count;
		}
	}




	// DFS ---------------------------------

	public int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		// Node values are from 1 to n, so we need n+1 sized list
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];

			// Check if there's already a path from u to v
			boolean[] visited = new boolean[n + 1];
			if (!graph.get(u).isEmpty() && !graph.get(v).isEmpty()) {
				if (dfs(u, v, graph, visited)) {
					return edge; // This edge creates a cycle
				}
			}

			// Add edge to graph (undirected)
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		return new int[0];
	}


	public boolean dfs(int current, int target, List<List<Integer>> graph, boolean[] visited) {
		if (current == target)
			return true;

		visited[current] = true;

		for (int neighbor : graph.get(current)) {
			if (!visited[neighbor]) {
				if (dfs(neighbor, target, graph, visited)) {
					return true;
				}
			}
		}

		return false;
	}
}