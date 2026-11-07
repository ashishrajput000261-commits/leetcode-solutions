import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // freeServers stores: int[]{weight, index}
        // Ordered by: weight ascending, then index ascending
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // busyServers stores: long[]{freeTime, weight, index}
        // Ordered by: freeTime ascending, then weight ascending, then index ascending
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        // Initially, all servers are free at time 0
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            // The j-th task becomes available at least at second j
            currentTime = Math.max(currentTime, (long) j);

            // If no servers are available, jump time directly to when the next server frees up
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }

            // Release all servers that finish their tasks at or before currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] released = busyServers.poll();
                freeServers.offer(new int[]{(int) released[1], (int) released[2]});
            }

            // Pick the best available server
            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;

            // Schedule the task on this server
            busyServers.offer(new long[]{currentTime + tasks[j], (long) weight, (long) index});
        }

        return ans;
    }
}