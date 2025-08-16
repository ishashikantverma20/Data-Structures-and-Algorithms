public class BestTimeToBuyAndSellStockII {
	public int maxProfit(int[] prices) {

		/**

		 Input: prices = [7,1,5,3,6,4]
		 Output: 7

		 As questionsaying we have to make max profit, and On each day, you may decide to buy and/or sell the stock.
		 So to make profit out of it, we can check today and yesterday, if today profit is greater then yesterday, so we can sell stock. And add amount to max profit variable.

		 Egde cases:
		 1. Empty array
		 2. Array with only one price
		 3. Strictly decreasing
		 4. All prices same

		 */

		// doing to this to handler edge case
		if(prices.length == 0 || prices.length < 2) {
			return 0;
		}

		// if all elements same or strictly decreasing array elements then 0 return
		int maxProfit = 0;


		// check in each iteration if today price is greater than yesterday so sell stock and add price to max profit.

		for(int i= 1; i<=prices.length - 1; i++) {
			if(prices[i] > prices[i-1]) {
				maxProfit += prices[i] - prices[i-1];
			}
		}
		return maxProfit;
	}
}
