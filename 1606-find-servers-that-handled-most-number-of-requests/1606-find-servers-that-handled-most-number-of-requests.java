import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TreeSet to maintain available server IDs in sorted order
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Min-Heap to track busy servers: stores [endTime, serverId]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currentTime = arrival[i];
            int duration = load[i];

            // 1. Free up servers that have finished processing by currentTime
            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                int freedServer = busy.poll()[1];
                available.add(freedServer);
            }

            // 2. If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // 3. Find the target server (i % k) or the next available one
            Integer assignedServer = available.ceiling(i % k);
            if (assignedServer == null) {
                // Wrap around to the smallest available server ID
                assignedServer = available.first();
            }

            // 4. Assign the request to the chosen server
            available.remove(assignedServer);
            busy.offer(new int[]{currentTime + duration, assignedServer});
            
            requestCount[assignedServer]++;
            maxRequests = Math.max(maxRequests, requestCount[assignedServer]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}