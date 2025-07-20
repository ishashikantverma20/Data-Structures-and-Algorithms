/**
 *  Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3]
 *
 * Output: [1,2,3]
 *
 *       1
 *     /  \
 *    N    2
 *       /  \
 *      3   N
 * **/
public class PreorderTraversal {
	List<Integer> result = new ArrayList();

	public List<Integer> preorderTraversal(TreeNode root) {
		helper(root);

		return result;
	}

	// Preorder ->  Root Left Right
	public void helper(TreeNode root) {
		// root null return empty result

		if (root == null)
			return;

		// add root value
		result.add(root.val);

		// traverse left
		helper(root.left);


		// traverse right
		helper(root.right);
	}

	/*
	   Root → Left → Right

              1
             / \
            2   3
           / \
          4   5

			// Input: [1, 2, 3, 4, 5]
           // Output: [1, 2, 4, 5, 3]
        */
	public List<Integer> preorderTraversalNonRecursive(TreeNode root) {
		List<Integer> result = new ArrayList<>();

		if (root == null) return result;

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			// pop up root element
			TreeNode current = stack.pop();
			result.add(current.val);  // Visit the root

			// Push right child first so that left is processed first
			if (current.right != null) stack.push(current.right);
			if (current.left != null) stack.push(current.left);
		}

		return result;
	}
}