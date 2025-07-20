public class PathSumII {

	/***
	 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
	 *
	 * A leaf is a node with no children.
	 *
	 */


	public boolean hasPathSum(TreeNode root, int targetSum) {

		return helper(root, targetSum, 0);

	}

	public boolean helper (TreeNode root, int targetSum, int currsum) {
		if(root == null) return false;

		// update current sum
		currsum += root.val;

		// check for the leaf node and if yes check for sum
		if(root.left == null && root.right == null) {
			return currsum == targetSum;
		}

		// traverse left
		boolean leftResult = helper(root.left, targetSum, currsum);
		// traverse right
		boolean rightResult = helper(root.right, targetSum,currsum);

		// return result present in left or right side
		return leftResult || rightResult;
	}
}
