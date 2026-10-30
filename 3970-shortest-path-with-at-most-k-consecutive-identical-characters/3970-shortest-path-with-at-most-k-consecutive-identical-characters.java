import java.util.*;

class Solution {
    static class State implements Comparable<State> {
        int dist;
        int u;
        int count;

        State(int dist, int u, int count) {
            this.dist = dist;
            this.u = u;
            this.count = count;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) return 0;

        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // dist[u][count] stores the minimum weight to reach node u 
        // ending with 'count' consecutive characters equal to labels.charAt(u)
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        
        // Start at node 0 with 1 consecutive character (labels[0]) and distance 0
        dist[0][1] = 0;
        pq.offer(new State(0, 0, 1));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int d = curr.dist;
            int u = curr.u;
            int count = curr.count;

            // The first time we pop the destination node, it is guaranteed to be optimal
            if (u == n - 1) {
                return d;
            }

            // Skip stale state
            if (d > dist[u][count]) {
                continue;
            }

            char charU = labels.charAt(u);

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];
                char charV = labels.charAt(v);

                int nextCount = (charU == charV) ? count + 1 : 1;

                if (nextCount <= k) {
                    int nextDist = d + weight;

                    // Dominance check: Is there already a path to v with <= nextCount 
                    // consecutive characters AND a smaller or equal distance?
                    boolean dominated = false;
                    for (int c = 1; c <= nextCount; c++) {
                        if (dist[v][c] <= nextDist) {
                            dominated = true;
                            break;
                        }
                    }

                    if (!dominated) {
                        dist[v][nextCount] = nextDist;
                        pq.offer(new State(nextDist, v, nextCount));
                    }
                }
            }
        }

        return -1;
    }
}