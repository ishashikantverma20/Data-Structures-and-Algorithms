import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 *  when even data
 *
 *  arr =[3 4 5 8 9 10],  median -> (5+8) / 2
 *
 *  5   8
 *  4   9
 *  3   10
 * Max  Min Heap
 *
 * when odd data
 *
 *  arr = [3, 4, 5, 8, 9], median -> 5
 *
 *  5     8
 *  4     9
 *  3
 *
 *  Max   Min Heap
 *
 *
 * **/
public class FindMedianFromDataStream {
	private PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
	private PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

	public MedianFinder() {}

	public void addNum(int num) {

		// adding num to heap, if num is less than or = to left heap top element then add in left max heap
		if (leftMaxHeap.isEmpty() || num <= leftMaxHeap.peek()) {
			leftMaxHeap.offer(num);
		} else {
			rightMinHeap.offer(num);
		}

		// Rebalance heaps to maintain size property
		// left heap size can only be greater than 1 only
		if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
			rightMinHeap.offer(leftMaxHeap.poll());
		} else if (rightMinHeap.size() > leftMaxHeap.size()) {
			leftMaxHeap.offer(rightMinHeap.poll());
		}
	}

	public double findMedian() {
		if (leftMaxHeap.size() == rightMinHeap.size()) {
			return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
		}
		
		// return left heap element when left heap size is greater than right heap size
		return leftMaxHeap.peek();
	}
}