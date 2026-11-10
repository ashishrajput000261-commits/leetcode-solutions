import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2); // Avoid integer overflow when adding
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1];
            for (int jump = 1; jump <= 3 && i - jump >= 0; jump++) {
                dp[i] = Math.min(dp[i], dp[i - jump] + stepCost + jump * jump);
            }
        }

        return dp[n];
    }
}