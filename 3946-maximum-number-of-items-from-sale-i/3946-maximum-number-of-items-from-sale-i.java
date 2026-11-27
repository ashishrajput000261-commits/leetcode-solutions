class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Precompute how many items have a factor that is a multiple of f
        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= maxFactor; m += f) {
                multiplesCount[f] += freq[m];
            }
        }

        // 0/1 Knapsack for buying at most 1 copy of each item
        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            // 1 purchased copy + (multiplesCount[factor] - 1) free copies = multiplesCount[factor]
            int value = multiplesCount[factor];

            for (int c = budget; c >= price; c--) {
                dp[c] = Math.max(dp[c], dp[c - price] + value);
            }
        }

        // Combine knapsack results with buying extra copies of the cheapest item
        int ans = 0;
        for (int c = 0; c <= budget; c++) {
            int extraCopies = (budget - c) / minPrice;
            ans = Math.max(ans, dp[c] + extraCopies);
        }

        return ans;
    }
}