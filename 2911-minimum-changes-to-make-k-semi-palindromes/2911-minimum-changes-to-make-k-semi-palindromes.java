import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // cost[i][j] stores min changes to make substring s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(s, i, j);
            }
        }
        
        // dp[p][j] = min changes to partition first j characters (s[0..j-1]) into p semi-palindromes
        int[][] dp = new int[k + 1][n + 1];
        int INF = 1_000_000;
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int p = 1; p <= k; p++) {
            // Each semi-palindrome must have length >= 2, so j starts at 2 * p
            for (int j = 2 * p; j <= n; j++) {
                // Previous partition ends at i, leaving substring s[i..j-1] of length >= 2
                for (int i = 2 * (p - 1); i <= j - 2; i++) {
                    dp[p][j] = Math.min(dp[p][j], dp[p - 1][i] + cost[i][j - 1]);
                }
            }
        }
        
        return dp[k][n];
    }
    
    // Helper function to find min changes to make s[left..right] a semi-palindrome
    private int getMinChanges(String s, int left, int right) {
        int len = right - left + 1;
        int minChanges = len; // Upper bound
        
        // Try all proper divisors d of len (1 <= d < len)
        for (int d = 1; d * 2 <= len; d++) {
            if (len % d != 0) continue;
            
            int currentChanges = 0;
            // There are d groups, each starting at offset 0, 1, ..., d - 1
            for (int offset = 0; offset < d; offset++) {
                int l = left + offset;
                int r = right - (d - 1 - offset);
                
                // Two-pointer palindrome check for the group with step size d
                while (l < r) {
                    if (s.charAt(l) != s.charAt(r)) {
                        currentChanges++;
                    }
                    l += d;
                    r -= d;
                }
            }
            minChanges = Math.min(minChanges, currentChanges);
        }
        
        return minChanges;
    }
}