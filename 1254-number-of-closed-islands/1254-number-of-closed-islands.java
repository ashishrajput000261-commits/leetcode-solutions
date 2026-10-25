class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Step 1: Sink all land cells connected to the 4 boundaries
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r == 0 || r == rows - 1 || c == 0 || c == cols - 1) && grid[r][c] == 0) {
                    dfsSink(grid, r, c);
                }
            }
        }

        // Step 2: Count and sink all remaining (guaranteed closed) islands
        int closedCount = 0;
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    closedCount++;
                    dfsSink(grid, r, c);
                }
            }
        }

        return closedCount;
    }

    private void dfsSink(int[][] grid, int r, int c) {
        // Out of bounds or already water
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }

        // Sink current land cell by turning 0 -> 1
        grid[r][c] = 1;

        // Traverse all 4 directions
        dfsSink(grid, r + 1, c);
        dfsSink(grid, r - 1, c);
        dfsSink(grid, r, c + 1);
        dfsSink(grid, r, c - 1);
    }
}