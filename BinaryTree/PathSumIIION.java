import java.util.HashMap;
import java.util.Map;

/**
 *  Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * */
public class PathSumIION {

		/**

		 use hashmap to count sum till the node

		 {<0,1>, <10,1> <15,1> <18,1>}

		 18-8 = 10, and yes 10 is present in map, so increment result

		 and once work done remove key from map for correct result


		 */
		int counter = 0;
		int tar = 0;
		public int pathSum(TreeNode root, int sum) {

			tar = sum;

			Map<Long, Integer> map = new HashMap();
			map.put(0L,1); // handle edge case

			helper(root, map,0L);

			return counter;
		}

		public void helper(TreeNode root, Map<Long, Integer> map, long currentsum) {
			if(root == null) {
				return;
			}

			// update curr sum
			currentsum += root.val;

			// check currsum - targetSum exist in map if yes update result
			if(map.containsKey(currentsum-tar)) {
				counter += map.get(currentsum-tar);
			}

			// update map with curr sum
			map.put(currentsum, map.getOrDefault(currentsum, 0) + 1);


			// traverse left
			helper(root.left, map, currentsum);

			// traverse right
			helper(root.right, map, currentsum);

			// remove used element from map
			if(map.get(currentsum) == 1) {
				map.remove(currentsum);
			} else {
				map.put(currentsum, map.getOrDefault(currentsum, 0) - 1);
			}
		}

}
