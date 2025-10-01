public class DFSTemplate {

    public void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        // Mark the node as visited
        visited[node] = true;

        // Process the current node (optional, depending on the problem)
        System.out.println("Visiting: " + node);

        // Traverse all neighbors
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    public void runDFS(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);  // Start DFS from unvisited node
            }
        }
    }
}
