public class BestTimeToBuyAndSellStockI {

	public  int maxProfitWithBF(int[] prices) {
      /*
       * Brute force tried each element with each element to check profit by subtracting lower price from higher price.
       * After that checking max profit is grater than current profit. and returning max profit.
       *
       *  TC - O(N^2)
       *  SC - O(1)
       *  Input: prices = [7,1,5,3,6,4]
       * Output: 5
       *
       * [7,1,5,3,6,4]
       *    mi    mx
       * **/

		int maxProfit = 0;

		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				int profit = prices[j] - prices[i];
				if (profit > maxProfit) {
					maxProfit = profit;
				}
			}
		}

		return maxProfit;
	}

	public  int maxProfitWithSinglePass(int[] prices) {
		/**
		 *  [7,1,5,3,6,4,0,9]
		 *              mi mx
		 *  Min price is calculated at each iteration, and when its not a min price it means, price is grater than min price. So calculate
		 *  profit on this. Also comparing here maxProfit with current profit at that point.
		 *
		 *  TC - O(N)
		 *  SC - O(1)
		 * **/

		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;

		for (int price : prices) {
			if (price < minPrice) {
				minPrice = price;
			} else {
				maxProfit = Math.max(maxProfit, price - minPrice);
			}
		}

		return maxProfit;
	}


}
