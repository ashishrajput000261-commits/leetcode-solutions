import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    long islandSum = 0;
                    Queue<int[]> queue = new ArrayDeque<>();
                    
                    // Start BFS from cell (r, c)
                    queue.offer(new int[]{r, c});
                    islandSum += grid[r][c];
                    grid[r][c] = 0; // Mark as visited

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int currR = curr[0];
                        int currC = curr[1];

                        for (int i = 0; i < 4; i++) {
                            int nr = currR + dr[i];
                            int nc = currC + dc[i];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandSum += grid[nr][nc];
                                grid[nr][nc] = 0; // Mark as visited immediately to avoid duplicate queueing
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}