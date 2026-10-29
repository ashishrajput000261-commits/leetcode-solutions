import java.util.*;

class Solution {
    private int[] head, to, next;
    private int edgeCount = 0;
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer = 0;
    private int LOG = 17; // 2^16 = 65536 > 50000
    private int[] bit;
    private int n;

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][LOG];
        bit = new int[n + 2];

        // Iterative DFS to compute Euler Tour and Binary Lifting tables (avoids StackOverflowError)
        int[] stack = new int[n];
        int[] edgeIter = new int[n];
        int top = 0;

        stack[0] = 0;
        edgeIter[0] = head[0];
        tin[0] = ++timer;

        while (top >= 0) {
            int u = stack[top];
            int e = edgeIter[top];

            if (e != -1) {
                edgeIter[top] = next[e];
                int v = to[e];
                if (v != up[u][0]) {
                    depth[v] = depth[u] + 1;
                    up[v][0] = u;
                    for (int j = 1; j < LOG; j++) {
                        up[v][j] = up[up[v][j - 1]][j - 1];
                    }
                    tin[v] = ++timer;
                    top++;
                    stack[top] = v;
                    edgeIter[top] = head[v];
                }
            } else {
                tout[u] = timer;
                top--;
            }
        }

        // Initialize node character masks and Fenwick Tree
        int[] currentMask = new int[n];
        for (int i = 0; i < n; i++) {
            currentMask[i] = 1 << (s.charAt(i) - 'a');
            updateRange(tin[i], tout[i], currentMask[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            if (q.charAt(0) == 'u') {
                // Format: "update u c"
                int firstSpace = 6; 
                int secondSpace = q.indexOf(' ', 7);
                int u = Integer.parseInt(q.substring(firstSpace + 1, secondSpace));
                char c = q.charAt(secondSpace + 1);

                int newMask = 1 << (c - 'a');
                int diff = currentMask[u] ^ newMask;
                if (diff != 0) {
                    currentMask[u] = newMask;
                    updateRange(tin[u], tout[u], diff);
                }
            } else {
                // Format: "query u v"
                int firstSpace = 5;
                int secondSpace = q.indexOf(' ', 6);
                int u = Integer.parseInt(q.substring(firstSpace + 1, secondSpace));
                int v = Integer.parseInt(q.substring(secondSpace + 1));

                int lca = getLCA(u, v);
                int pathMask = queryPoint(tin[u]) ^ queryPoint(tin[v]) ^ currentMask[lca];

                // True if at most 1 bit is set in pathMask
                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private void updatePoint(int idx, int val) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private void updateRange(int l, int r, int val) {
        updatePoint(l, val);
        updatePoint(r + 1, val);
    }

    private int queryPoint(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[u][j];
            }
        }
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }
}