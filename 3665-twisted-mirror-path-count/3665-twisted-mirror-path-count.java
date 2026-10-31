class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[i][j][0] -> ways reaching (i, j) by moving Right
        // dp[i][j][1] -> ways reaching (i, j) by moving Down
        int[][][] dp = new int[m][n][2];
        
        // Base case: start at (0, 0)
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // Empty cell: can choose to move either Right or Down regardless of entry direction
                    long totalWays = (dp[i][j][0] + dp[i][j][1]) % MOD;
                    if (totalWays == 0) continue;

                    // Move Right to (i, j + 1)
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (int) ((dp[i][j + 1][0] + totalWays) % MOD);
                    }
                    // Move Down to (i + 1, j)
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (int) ((dp[i + 1][j][1] + totalWays) % MOD);
                    }
                } else {
                    // Mirror cell: forced reflection based on incoming direction
                    
                    // 1. Entered moving Right -> reflected Down to (i + 1, j)
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (dp[i + 1][j][1] + dp[i][j][0]) % MOD;
                    }
                    
                    // 2. Entered moving Down -> reflected Right to (i, j + 1)
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (dp[i][j + 1][0] + dp[i][j][1]) % MOD;
                    }
                }
            }
        }

        // Total ways to reach the bottom-right cell (m - 1, n - 1)
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}