import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;
        int qLen = queries.length;

        // 1. Sort logs by timestamp ascending
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // 2. Pair queries with their original indices and sort by query time
        // queryPairs[i][0] = query time, queryPairs[i][1] = original index
        int[][] queryPairs = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            queryPairs[i][0] = queries[i];
            queryPairs[i][1] = i;
        }
        Arrays.sort(queryPairs, Comparator.comparingInt(a -> a[0]));

        int[] ans = new int[qLen];
        // serverCount[id] tracks the frequency of requests for server id in current window
        int[] serverCount = new int[n + 1];
        int activeServers = 0;

        int left = 0;
        int right = 0;

        // 3. Sliding window over sorted queries
        for (int i = 0; i < qLen; i++) {
            int qTime = queryPairs[i][0];
            int originalIdx = queryPairs[i][1];

            // Expand right boundary: include logs where time <= qTime
            while (right < m && logs[right][1] <= qTime) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }

            // Shrink left boundary: exclude logs where time < qTime - x
            int windowStart = qTime - x;
            while (left < m && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }

            // Total servers minus servers with at least one request
            ans[originalIdx] = n - activeServers;
        }

        return ans;
    }
}