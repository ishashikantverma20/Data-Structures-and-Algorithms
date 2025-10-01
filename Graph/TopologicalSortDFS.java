import java.util.*;

/**
 *
 *  Its apply only on Directed Acyclic Graph
 *
 *  u -> v // u should be always comes first before v
 *
 *  Topological Sort:
 * 4 5 2 3 1 0
 *
 * **/
public class TopologicalSortDFS {

		// Graph class using adjacency list
		static class Graph {
			int V; // Number of vertices
			List<List<Integer>> adj;

			Graph(int V) {
				this.V = V;
				adj = new ArrayList<>();
				for (int i = 0; i < V; i++) {
					adj.add(new ArrayList<>());
				}
			}

			// Add a directed edge u -> v
			void addEdge(int u, int v) {
				adj.get(u).add(v);
			}

			// Topological Sort using DFS
			void topologicalSort() {
				boolean[] visited = new boolean[V];
				Stack<Integer> stack = new Stack<>();

				for (int i = 0; i < V; i++) {
					if (!visited[i]) {
						dfs(i, visited, stack);
					}
				}

				// Print the topological order
				while (!stack.isEmpty()) {
					System.out.print(stack.pop() + " ");
				}
			}

			// Recursive DFS
			void dfs(int v, boolean[] visited, Stack<Integer> stack) {
				visited[v] = true;

				for (int neighbor : adj.get(v)) {
					if (!visited[neighbor]) {
						dfs(neighbor, visited, stack);
					}
				}

				stack.push(v); // Push after visiting all neighbors
			}
		}

		// Main method
		public static void main(String[] args) {
			Graph g = new Graph(6);
			g.addEdge(5, 0);
			g.addEdge(5, 2);
			g.addEdge(4, 0);
			g.addEdge(4, 1);
			g.addEdge(2, 3);
			g.addEdge(3, 1);

			System.out.println("Topological Sort:");
			g.topologicalSort();
		}
}

