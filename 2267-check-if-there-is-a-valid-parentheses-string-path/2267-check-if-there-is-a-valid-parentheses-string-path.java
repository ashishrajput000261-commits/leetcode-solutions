class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1. A valid parentheses string must have an even length.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // 2. Fast fail: Cannot start with ')' or end with '('
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // Maximum possible balance we can reach without failing the "remaining steps" check
        int maxBalance = (m + n) / 2;
        
        // visited[row][col][balance] -> stores whether this state has been explored and failed
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];
        
        return dfs(grid, 0, 0, 0, visited, m, n);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, boolean[][][] visited, int m, int n) {
        // Update balance based on the current cell
        balance += (grid[r][c] == '(') ? 1 : -1;
        
        // Pruning Rule 1: More closing brackets than opening ones
        if (balance < 0) {
            return false;
        }
        
        // Pruning Rule 2: Impossible to close all currently open brackets with remaining steps
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingSteps) {
            return false;
        }
        
        // Base Case: Reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        // If we have visited this exact state before (and it returned false), skip it
        if (visited[r][c][balance]) {
            return false;
        }
        
        // Mark current state as visited (memoizing failure)
        visited[r][c][balance] = true;
        
        // Explore moving Down
        if (r + 1 < m && dfs(grid, r + 1, c, balance, visited, m, n)) {
            return true;
        }
        // Explore moving Right
        if (c + 1 < n && dfs(grid, r, c + 1, balance, visited, m, n)) {
            return true;
        }
        
        return false;
    }
}