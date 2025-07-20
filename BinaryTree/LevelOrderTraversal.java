/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * <p>
 * 3
 * / \
 * 9  20
 * / \
 * 15  7
 * <p>
 * output - [[3], [9, 20], [15, 7]]
 **/

public class LevelOrderTraversal {

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList();
		Queue<TreeNode> que = new LinkedList();

		if (root == null)
			return result;

		// add root to queue
		que.offer(root);

		// iterate till stack empty
		while (!que.isEmpty()) {

			// get current elements present in queue
			int levelSize = que.size();


			List<Integer> list = new LinkedList();

			for (int i = 0; i < levelSize; i++) {

				// take first element
				TreeNode node = que.poll();

				list.add(node.val);

				// travese left
				if (node.left != null) {
					que.offer(node.left);
				}

				// travese right
				if (node.right != null) {
					que.offer(node.right);
				}
			}

			result.add(list);

		}

		return result;
	}
}