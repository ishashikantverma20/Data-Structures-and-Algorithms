/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * */

public class PathSumII {
	List<List<Integer>> result = new ArrayList();
	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList();
		helper(root, targetSum, list, 0);
		return result;
	}

	// helper is recursive function which create a result
	public void helper(TreeNode root, int targetSum, List<Integer> list, int currsum) {

		if (root == null) {
			return;
		}

		// update cuurent sum
		currsum += root.val;

		// update the list
		list.add(root.val);

		// check for the leaf node and if yes check target sum match current sum
		if (targetSum == currsum && root.left == null && root.right == null) {
			result.add(new ArrayList(list));
		}

		// traverse left
		helper(root.left, targetSum, list, currsum);

		// traverse right
		helper(root.right, targetSum, list, currsum);

		// remove used element from list
		list.remove(list.size() - 1);
	}
}