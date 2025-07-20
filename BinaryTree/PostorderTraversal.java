/**
 *
 * Left → Right → Root
 * Output: [4,6,7,5,2,9,8,3,1]
 *
 *           1
 *         /   \
 *        2     3
 *       / \     \
 *      4   5     8
 *         / \   /
 *        6   7 9
 *
 *
 * **/


public class PostorderTraversal {

	List<Integer>  result = new ArrayList();

	public List<Integer> postorderTraversalRecusive(TreeNode root) {
		helper(root);

		return result;
	}

	// Postorder ->   Left Right Root
	public void helper(TreeNode root) {
		// root null return empty result

		if(root == null) return;

		// traverse left
		helper(root.left);

		// traverse right
		helper(root.right);

		// add root value
		result.add(root.val);
	}

	public List<Integer> postorderTraversalNonRecurI(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		if (root == null) return ans;

		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			// adding first
			ans.addFirst(cur.val);

			if (cur.left != null) {
				stack.push(cur.left);
			}

			if (cur.right != null) {
				stack.push(cur.right);
			}
		}
		return ans;
	}

	public List<Integer> postorderTraversalNonRecurII(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;

		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode current = root;
		TreeNode lastVisited = null;

		while (current != null || !stack.isEmpty()) {
			// Go as far left as possible
			while (current != null) {
				stack.push(current);
				current = current.left;
			}

			TreeNode peekNode = stack.peek();

			// If right child exists and hasn't been visited yet, go right
			if (peekNode.right != null && lastVisited != peekNode.right) {
				current = peekNode.right;
			} else {
				stack.pop();
				result.add(peekNode.val);
				lastVisited = peekNode;
			}
		}

		return result;
	}


}