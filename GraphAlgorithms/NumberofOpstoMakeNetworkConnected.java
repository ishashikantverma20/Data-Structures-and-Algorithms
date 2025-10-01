/**
 * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
 *
 * You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
 *
 * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
 *
 *
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 *
 *
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 *
 *
 * **/

public class NumberofOpstoMakeNetworkConnected {

	/***

	 n = 6
	 connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]

	 parent = [0,0,0,0,4,5] in final state

	 components:
	 [0,1,2,3] - 1 component
	 4 - is alone
	 5 - is alone

	 count  = 3 for 3 components {[0,1,2,3], 4, 5}

	 To connect 3 components, we need 3-1= 2 operations, that's why return getCount() - 1


	 */

	// Union Find
	class Solution {
		public int makeConnected(int n, int[][] connections) {

			// To connect n connections we need n-1 connection, so this check mandatory
			if(connections.length < n - 1) {
				return -1; // not cables present
			}

			UnionFind uf = new UnionFind(n);

			for(int[] conn : connections) {
				uf.union(conn[0], conn[1]);
			}

			return uf.getCount() - 1;
		}
	}

	public class UnionFind {
		private int[] parent;
		private int[] size;   // optional
		private int count;  // Number of disjoint sets

		public UnionFind(int n) {
			parent = new int[n];
			size = new int[n];
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
		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX == rootY) {
				return;
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



   // DFS --------------------------------------------------------

	 class Solution {
		public int makeConnected(int n, int[][] connections) {
			if (connections.length < n - 1) {
				return -1;  // Not enough cables
			}

			// Build adjacency list
			List<List<Integer>> graph = new ArrayList<>();
			for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

			for (int[] conn : connections) {
				graph.get(conn[0]).add(conn[1]);
				graph.get(conn[1]).add(conn[0]);
			}

			boolean[] visited = new boolean[n];
			int components = 0;

			// Count connected components via DFS
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					components++;
					dfs(i, graph, visited);
				}
			}

			// Minimum operations = number of components - 1
			return components - 1;
		}

		private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
			visited[node] = true;
			for (int neighbor : graph.get(node)) {
				if (!visited[neighbor]) {
					dfs(neighbor, graph, visited);
				}
			}
		}
	}


}