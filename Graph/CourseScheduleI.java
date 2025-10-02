/**


 Input: numCourses = 2, prerequisites = [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.

 Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

  [1, 0]    0 --> 1
   u  v

 1. create an adjacency list of mapping from preq -> to depedent coures
 2. Populate in-degree array
 3. Create a queue and populate it with all nodes whose in-degree 0
 4. while queue not empty
     a. fetch node from queue
     b. and for all node neighbour decrease in-degree by 1
     c. if node in-degree 0, then add in queue
 5. Result:- count each time when nodes poped from queue
 */

public class CourseScheduleI {

		public boolean canFinish(int V, int[][] preqs) {
			int[] indegree = new int[V];

			List<List<Integer>> adj = new ArrayList<>();

			for(int i=0; i < V; i++) {
				adj.add(new ArrayList());
			}

			for(int[] pair : preqs) {
				int course = pair[0];
				int preq = pair[1];

				// this is direcred graph
				adj.get(preq).add(course);
			}

			// first set indegree for all incoming nodes

			for(int u = 0; u < V; u++) {

				for(int v : adj.get(u)) {
					indegree[v]++;
				}
			}

			// queue for nodes with 0 in-degree

			Queue<Integer> queue = new LinkedList();

			for(int i =0; i<V; i++) {
				if(indegree[i] == 0) {
					queue.add(i);
				}
			}

			// result
			int count = 0;

			while(!queue.isEmpty()) {
				int node = queue.poll();
				count++;

				for(int v : adj.get(node)) {
					indegree[v]--;

					if(indegree[v] == 0) {
						queue.add(v);
					}
				}
			}

			if(count != V) {
				return false;  // a cycle detect in graph or cicular depency in courses
			}

			return true;
		}
}