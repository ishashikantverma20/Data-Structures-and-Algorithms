/**
 *  Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 *
 * **/
public class JumpGameIII {
	public boolean canReach(int[] arr, int start) {
		boolean[] visited = new boolean[arr.length];
		return dfs(arr, start, visited);
	}

	private boolean dfs(int[] arr, int pos, boolean[] visited) {
		if (pos < 0 || pos >= arr.length || visited[pos]) return false;
		if (arr[pos] == 0) return true;

		visited[pos] = true;

		return dfs(arr, pos + arr[pos], visited) || dfs(arr, pos - arr[pos], visited);
	}

}