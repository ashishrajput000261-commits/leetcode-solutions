import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] ans;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Build the initial tree
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // Single DFS pass to both re-parent and compute final subtree sizes
        dfs(0);

        return ans;
    }

    private void dfs(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];
        
        // Record u as the most recent ancestor for its character
        lastSeen[charIdx] = u;

        // Initialize subtree size of u as 1 (counting itself)
        int currentSize = 1;

        for (int v : adj[u]) {
            dfs(v);
            
            // Determine where v's subtree attaches in the final tree
            int vCharIdx = s.charAt(v) - 'a';
            int newParent = lastSeen[vCharIdx];
            
            if (newParent != -1 && newParent != u) {
                // v moves to a higher ancestor; add v's final subtree size directly to newParent
                ans[newParent] += ans[v];
            } else {
                // v stays attached to u
                currentSize += ans[v];
            }
        }

        // Add any sizes transferred from deeper descendants directly to u
        ans[u] += currentSize;

        // Backtrack: restore the previous ancestor for u's character
        lastSeen[charIdx] = prevAncestor;
    }
}