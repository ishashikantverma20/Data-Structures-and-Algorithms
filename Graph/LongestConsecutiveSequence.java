/**
 *  Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *  Input: nums = [100,4,200,1,3,2]
 *  Output: 4
 *  Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 *  Disjoint set
 *
 *       1
 *     / | \
 *    2  3  4
 *
 *   creating one super set, including smaller set. All set are there own parent. But grouping them into
 *   under one parent or 1 set.
 * **/

public class LongestConsecutiveSequence {

		public int longestConsecutive(int[] nums) {

			if(nums.length == 0) return 0;

			Map<Integer, Integer> map = new HashMap();
			UnionFind uf = new UnionFind(nums.length);

			for(int i=0; i < nums.length; i++) {

				if(map.containsKey(nums[i])) {
					continue; // duplicate element
				}

				map.put(nums[i], i);

				if(map.containsKey(nums[i] - 1)) {
					uf.union(i, map.get(nums[i] - 1));
				}

				if(map.containsKey(nums[i] + 1)) {
					uf.union(i, map.get(nums[i] +  1));
				}
			}

			return uf.getMaxCount();

		}
	}

	class UnionFind {
		int[] parent;
		int[] size;
		int maxCount;

		public UnionFind(int n) {
			parent = new int[n];
			size = new int[n];
			maxCount = 1;

			for(int i=0; i <n; i++) {
				parent[i] = i;
				size[i]  = 1;
			}
		}

		public int find(int x) {
			if(parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}

		public void union(int x, int y) {

			int rootX = find(x);
			int rootY = find(y);

			if(rootX == rootY){
				return;
			}

			if(size[rootX] < size[rootY]) {
				parent[rootX] = rootY;
				size[rootY] += size[rootX];
				maxCount = Math.max(maxCount, size[rootY]);
			} else {
				parent[rootY] = rootX;
				size[rootX] += size[rootY];
				maxCount = Math.max(maxCount, size[rootX]);
			}

		}

		public int getMaxCount() {
			return maxCount;
		}
}