/*
// Definition for a Node.
class Node {
public int val;
public List<Node> neighbors;
public Node() {
	val = 0;
	neighbors = new ArrayList<Node>();
}
public Node(int _val) {
	val = _val;
	neighbors = new ArrayList<Node>();
}
public Node(int _val, ArrayList<Node> _neighbors) {
	val = _val;
	neighbors = _neighbors;
}
}
*/

/**

    1  --  2
    |      |
    4  --  3

 1  neighbours  2, 4
 2  neighbours  1, 3
 3  neighbours  2, 4
 4  neighbours  1, 3


 Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
                      1     2    3     4
 Output: [[2,4],[1,3],[2,4],[1,3]]

 1. check Map if contains cloned node then return
 2. if Map not contains node, then create new clone node and add to Map
 3. Before returning that new created clone node, clone its neighbours from original node
 */
public class CloneGraph {

		Map<Node, Node> oldToNew = new HashMap();

		public Node cloneGraph(Node node) {
			return clone(node);
		}

		public Node clone(Node node) {

			if(node == null) {
				return null;
			}

			// if cloned copy present then return that
			if(oldToNew.containsKey(node)) {
				return oldToNew.get(node);
			}

			// create new clone node from old

			Node cloneNode = new Node(node.val);

			oldToNew.put(node, cloneNode);

			// for each old node neighbours create a clone node neighbours

			for(Node nebh : node.neighbors) {
				// for this nebh find cloned neighbour
				cloneNode.neighbors.add(clone(nebh));
			}
			// retun new clone node with its neighbours
			return cloneNode;
		}
}