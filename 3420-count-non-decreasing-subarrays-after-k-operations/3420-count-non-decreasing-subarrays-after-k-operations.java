class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        // Array-backed monotonic deque storing plateau start indices.
        // Since l moves from n - 1 down to 0, pushing to the front never underflows index 0.
        int[] dq = new int[n];
        int head = n, tail = n;

        long ans = 0;
        long cost = 0;
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            // Merge all front plateaus whose prefix max is <= nums[l]
            while (head < tail && nums[dq[head]] <= nums[l]) {
                int idx = dq[head++];
                int nextIdx = (head < tail) ? dq[head] : (r + 1);
                long len = nextIdx - idx;
                cost += len * (nums[l] - nums[idx]);
            }
            dq[--head] = l;

            // Shrink the right boundary until the window cost is at most k
            while (cost > k) {
                int backIdx = dq[tail - 1];
                cost -= (nums[backIdx] - nums[r]);
                if (backIdx == r) {
                    tail--;
                }
                r--;
            }

            // All subarrays [l..x] with l <= x <= r are valid
            ans += (r - l + 1);
        }

        return ans;
    }
}