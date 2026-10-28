class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][][] dp = new boolean[m][n][1024];
        
        int startVal = grid[0][0];
        dp[0][0][startVal] = true;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                
                if (i > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (dp[i - 1][j][v]) {
                            dp[i][j][v ^ val] = true;
                        }
                    }
                }
                
                if (j > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (dp[i][j - 1][v]) {
                            dp[i][j][v ^ val] = true;
                        }
                    }
                }
            }
        }
        
        for (int v = 0; v < 1024; v++) {
            if (dp[m - 1][n - 1][v]) {
                return v;
            }
        }
        
        return 0;
    }
}