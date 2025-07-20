/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 *
 * * Example 1:
 *  *
 *  * Input: root = [1,null,2,3]
 *  *
 *  * Output: [1,2,3]
 *  *
 *  *       1
 *  *    |    |
 *  *    N    2
 *  *       |  |
 *  *      3   N
 **/

public class InorderTraversal {

	List<Integer>  result = new ArrayList();

	public List<Integer> inorderTraversal(TreeNode root) {

		helper(root);

		return result;
	}

	// Inorder -> Left Root Right
	public void helper(TreeNode root) {
		// root null return empty result

		if(root == null) return;

		// traverse left
		helper(root.left);

		// add root value
		result.add(root.val);

		// traverse right
		helper(root.right);
	}

	public List<Integer> inorderTraversalNonRecurrsive(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;

		// Traverse the tree
		while (current != null || !stack.isEmpty()) {
			// Reach the leftmost node
			while (current != null) {
				stack.push(current);
				current = current.left;
			}

			// current is null here
			current = stack.pop();
			result.add(current.val);  // Visit the node
			current = current.right;  // Move to right subtree
		}

		return result;
	}
}