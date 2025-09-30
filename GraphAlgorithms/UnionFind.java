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

		if (rootX == rootY) return;  // Already connected

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
