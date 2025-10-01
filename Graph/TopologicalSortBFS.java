import java.util.*;

/**
 * Kahn's Algo using BFS,
 *
 *  5 -> 0 <-  4
 *  |          |
 *  V          V
 *  2 -> 3 ->  1
 *
 *
 * U -> V  // U always comes before V, in topological sort
 *
 * Topological Sort (Kahn's Algorithm):
 * 4 5 0 2 3 1
 *
 * 1. Add all nodes with in-degree 0 to queue.
 * 2. while the queue not empty:
 *     Remove a node from the queue.
 *     For each outgoing edge from removed node, decrement the in-degree of the destination node by 1.
 *     If the in-degree of a destination node becomes 0, add it to the queue.
 * 3. If the queue is empty and there are still nodes in the graph, the graph contains a cycle, and cannot be topologically sorted.
 * 4. The nodes in the queue represent the topological ordering of the graph.
 *
 *
 *
 *
 * **/
public class TopologicalSortBFS {

	static class Graph
		int V;
		List<List<Integer>> adj;

		Graph(int V) {
			this.V = V;
			adj = new ArrayList<>();
			for (int i = 0; i < V; i++) {
				adj.add(new ArrayList<>());
			}
		}

		void addEdge(int u, int v) {
			adj.get(u).add(v);
		}

		void topologicalSort() {
			int[] inDegree = new int[V];

			// Step 1: Compute in-degree of each node
			for (int i = 0; i < V; i++) {
				for (int neighbor : adj.get(i)) {
					inDegree[neighbor]++;
				}
			}

			// Step 2: Enqueue nodes with in-degree 0
			Queue<Integer> queue = new LinkedList<>();
			for (int i = 0; i < V; i++) {
				if (inDegree[i] == 0) {
					queue.offer(i);
				}
			}

			// Step 3: Process the queue
			List<Integer> topoOrder = new ArrayList<>();
			while (!queue.isEmpty()) {
				int node = queue.poll();
				topoOrder.add(node);

				for (int neighbor : adj.get(node)) {
					inDegree[neighbor]--;
					if (inDegree[neighbor] == 0) {
						queue.offer(neighbor);
					}
				}
			}

			// Step 4: Check if graph has a cycle
			if (topoOrder.size() != V) {
				System.out.println("Graph has a cycle. Topological sort not possible.");
			} else {
				System.out.println("Topological Sort:");
				for (int node : topoOrder) {
					System.out.print(node + " ");
				}
			}
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(5, 0);
		g.addEdge(5, 2);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		g.topologicalSort();
	}
}
