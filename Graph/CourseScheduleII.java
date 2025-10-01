/**
 *  Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 *
 * **/

public class CourseScheduleII {
		public int[] findOrder(int V, int[][] preqs) {
			int[] indegree = new int[V];

			List<List<Integer>> adj = new ArrayList<>();

			for(int i=0; i < V; i++) {
				adj.add(new ArrayList());
			}

			for(int[] pair : preqs) {
				int course = pair[0];
				int preq = pair[1];

				adj.get(preq).add(course);
			}

			// first set indegree for all incoming nodes

			for(int u = 0; u < V; u++) {

				for(int v : adj.get(u)) {
					indegree[v]++;
				}
			}

			// queue for nodes with in-degree

			Queue<Integer> queue = new LinkedList();

			for(int i =0; i<V; i++) {
				if(indegree[i] == 0) {
					queue.add(i);
				}
			}

			// result
			List<Integer> list= new ArrayList();

			while(!queue.isEmpty()) {
				int node = queue.poll();
				list.add(node);

				for(int v : adj.get(node)) {
					indegree[v]--;

					if(indegree[v] == 0) {
						queue.add(v);
					}
				}
			}

			if(list.size() != V) {
				return  new int[0];
			}

			return list.stream().mapToInt(Integer::intValue).toArray();
		}
}