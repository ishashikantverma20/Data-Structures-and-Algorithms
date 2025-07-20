import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 *
 * */
public class PathSumIION2 {
	/**
	 ([], 8)
	 ([10], 8)
	 ([10,5], 8)
	 ([10,5,3], 8) -> update result as found one path
	 ([10,5,3,3], 8)
	 ([10,5,3,-2], 8)
	 ([10,5,2], 8)
	 ([10,5,2,1], 8) -> -> update result as found one path
	 .
	 .
	 ... so on

	 */
	int counter = 0;
	int tar = 0;
	public int pathSum(TreeNode root, int sum) {

		tar = sum;

		List<Long> path = new ArrayList();

		helper(root, path);

		return counter;
	}

	public void helper(TreeNode root, List<Long> path) {
		if(root == null) {
			return;
		}

		// add current node value to path
		path.add((long) root.val);

		// iterate path from end and check current sum equal to target sum
		long currSum = 0;

		for(int i = path.size()-1; i>=0; i--) {
			currSum += path.get(i);

			if(currSum == tar) {
				counter++;
			}
		}
		// traverse left
		helper(root.left, path);

		// traverse right
		helper(root.right, path);

		// remove used value from path
		path.remove(path.size() - 1);

	}
}

